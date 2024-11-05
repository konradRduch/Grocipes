package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.BodyMeasurementsDTO;
import com.grocipes_backend.grocipes.models.DTO.NutritionalGoalDTO;
import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.models.DTO.ProgressDTO;
import com.grocipes_backend.grocipes.models.NutritionalGoal;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.BodyMeasurementsRepository;
import com.grocipes_backend.grocipes.repositories.NutritionalGoalRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@Service
public class NutritionalGoalService {

    private final NutritionalGoalRepository nutritionalGoalRepository;
    private final UserEntityRepository userEntityRepository;
    private final BodyMeasurementsRepository bodyMeasurementsRepository;


    public NutritionalGoalService(NutritionalGoalRepository nutritionalGoalRepository, UserEntityRepository userEntityRepository, BodyMeasurementsRepository bodyMeasurementsRepository) {
        this.nutritionalGoalRepository = nutritionalGoalRepository;
        this.userEntityRepository = userEntityRepository;
        this.bodyMeasurementsRepository = bodyMeasurementsRepository;
    }

    public void cos(ProfileInfoDTO profileInfoDTO){
        LocalDate birthDate = profileInfoDTO.getBirthday();
        LocalDate currentDate = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(birthDate, currentDate);
        double BMR = calculateBMR(profileInfoDTO.getGender(), profileInfoDTO.getWeight(),profileInfoDTO.getHeight(),age);
        double TDEE = calculateAverageTDEE(BMR, "High active");

        calculateDailyNutrientBreakdown(TDEE, "weight gain");

    }

    public void addNutritionalGoal(Integer userId, NutritionalGoalDTO nutritionalGoalDTO){
        UserEntity user = userEntityRepository.findUserEntitiesById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));
        NutritionalGoal nutritionalGoal = new NutritionalGoal();

        nutritionalGoal.setGoalStartDate(nutritionalGoalDTO.getGoalStartDate());
        nutritionalGoal.setGoalEndDate(nutritionalGoalDTO.getGoalEndDate());
        nutritionalGoal.setName(nutritionalGoalDTO.getName());
        nutritionalGoal.setTypeOfGoal(nutritionalGoalDTO.getTypeOfGoal());
        nutritionalGoal.setTargetWeight(nutritionalGoalDTO.getTargetWeight());
        nutritionalGoal.setTargetAbdominalCircumference(nutritionalGoalDTO.getTargetAbdominalCircumference());
        nutritionalGoal.setTargetBodyFatLevel(nutritionalGoalDTO.getTargetBodyFatLevel());
        nutritionalGoal.setActive(nutritionalGoal.isActive());
        nutritionalGoal.setUserEntity(user);

        nutritionalGoalRepository.save(nutritionalGoal);
    }
    @Transactional
    public void deleteNutritionalGoal(Integer id){
        nutritionalGoalRepository.deleteById(id);
    }

    public List<NutritionalGoalDTO> findAllGoals(Integer userId, ProfileInfoDTO profileInfoDTO) {
        // Pobieranie wszystkich celów żywieniowych dla danego użytkownika
        List<NutritionalGoal> nutritionalGoals = nutritionalGoalRepository.findNutritionalGoalsByUserEntityId(userId);

        // Ustawianie stanu aktywności celów
        LocalDate today = LocalDate.now();
        for (NutritionalGoal goal : nutritionalGoals) {
            boolean isActive = (goal.getGoalStartDate() != null && goal.getGoalEndDate() != null) &&
                    (today.isEqual(goal.getGoalStartDate()) || today.isEqual(goal.getGoalEndDate()) ||
                            (today.isAfter(goal.getGoalStartDate()) && today.isBefore(goal.getGoalEndDate())));
            goal.setActive(isActive);
         if(isActive)nutritionalGoalRepository.save(goal);
        }

        // Mapowanie encji NutritionalGoal na NutritionalGoalDTO
        List<NutritionalGoalDTO> nutritionalGoalDTOs = nutritionalGoals.stream()
                .map(goal -> new NutritionalGoalDTO(
                        goal.getId(),
                        goal.getName(),
                        goal.getTypeOfGoal(),
                        goal.getGoalStartDate(),
                        goal.getGoalEndDate(),
                        goal.isActive(),
                        goal.getTargetWeight(),
                        goal.getTargetAbdominalCircumference(),
                        goal.getTargetBodyFatLevel(),
                        goal.getProgress()
                ))
                .collect(Collectors.toList());

        return nutritionalGoalDTOs;
    }

    public NutritionalGoalDTO findGoalById(Integer id){
        return nutritionalGoalRepository.findById(id)
                .map(goal -> new NutritionalGoalDTO(
                        goal.getId(),
                        goal.getName(),
                        goal.getTypeOfGoal(),
                        goal.getGoalStartDate(),
                        goal.getGoalEndDate(),
                        goal.isActive(),
                        goal.getTargetWeight(),
                        goal.getTargetAbdominalCircumference(),
                        goal.getTargetBodyFatLevel(),
                        goal.getProgress()
                ))
                .orElse(null); // Zwraca null, jeśli encja nie istnieje
    }

    public void startGoal(Integer id, LocalDate startDate, LocalDate endDate){
        NutritionalGoal nutritionalGoal = nutritionalGoalRepository.findNutritionalGoalById(id);
        nutritionalGoal.setGoalStartDate(startDate);
        nutritionalGoal.setGoalEndDate(endDate);

    }

    private BodyMeasurementsDTO findBodyMeasurementsByNearestDate(Integer userId, LocalDate startDate) {
        return bodyMeasurementsRepository.findUserBodyMeasurements(userId).stream()
                .filter(measurement -> !measurement.getMeasurement_date().isAfter(startDate.atStartOfDay())) // Tylko daty <= startDate
                .max(Comparator.comparing(BodyMeasurementsDTO::getMeasurement_date))
                .orElseThrow(() -> new IllegalArgumentException("Body measurements not found"));
    }
    private double calculateBMR(String gender, double weight, double height, int age) {
        double BMR = (10.0 * weight) + (6.25 * height) - (5.0 * age);
        if (gender.equals("M")) {
            BMR += 5;
        } else {
            BMR -= 161;
        }
        return BMR;
    }

    private double calculateAverageTDEE(double BMR, String userActivity) {
        double firstCMP = 0, secondCMP = 0;
        switch (userActivity) {
            case "Low active" -> {
                firstCMP = BMR * 1.40;
                secondCMP = BMR * 1.69;
            }
            case "Moderately active" -> {
                firstCMP = BMR * 1.70;
                secondCMP = BMR * 1.99;
            }
            case "High active" -> {
                firstCMP = BMR * 2.00;
                secondCMP = BMR * 2.40;
            }
        }
        return (firstCMP + secondCMP) / 2.0;
    }


    public double estimatedTimeToAchieveGoal(double currentWeight, double targetWeight, String typeOfGoal){
    //return time in weeks
        if(typeOfGoal.equals("Weight loss")){
            return abs(currentWeight - targetWeight) / 0.75 ;
        }else if(typeOfGoal.equals("Weight gain")){
            return abs(currentWeight - targetWeight) / 0.375 ;
        }else{
            return 0.0;
        }
    }

    private void calculateDailyNutrientBreakdown(double calories, String typeOfGoal){
        double carbPercentage, proteinPercentage, fatPercentage;

        switch (typeOfGoal) {
            case "weight loss" -> {
                carbPercentage = 0.4;
                proteinPercentage = 0.3;
                fatPercentage = 0.3;
            }
            case "weight maintenance" -> {
                carbPercentage = 0.5;
                proteinPercentage = 0.2;
                fatPercentage = 0.3;
            }
            case "weight gain" -> {
                carbPercentage = 0.55;
                proteinPercentage = 0.2;
                fatPercentage = 0.25;
            }
            default -> throw new IllegalArgumentException("Invalid goal type");
        }

        // Calculate calories for each macronutrient
        double carbsCalories = calories * carbPercentage;
        double proteinCalories = calories * proteinPercentage;
        double fatCalories = calories * fatPercentage;

        // 4 kcal per gram for carbs and protein, 9 kcal per gram for fat
        double carbsGrams = carbsCalories / 4;
        double proteinGrams = proteinCalories / 4;
        double fatGrams = fatCalories / 9;
    }


    private Integer calculateProgress(double currentValue, double startValue, double targetValue) {
        double totalChange = Math.abs(targetValue - startValue);
        double currentChange = Math.abs(currentValue - startValue);

        if ((totalChange == 0) || (currentValue < Math.min(startValue, targetValue)) ||
                (currentValue > Math.max(startValue, targetValue))) {
            return 0;
        }
        double progress = (currentChange / totalChange) * 100.0;
        return (int) Math.round(progress);
    }

    private Double calculateBMI(double weight, double height){
        height = height/100;
        return weight * (height * height);
    }

    private Double calculateBodyFatLevel(Double BMI, Integer age, String gender){
        if(age<=15){
            double childBodyFatLevel = (1.51 * BMI) - (0.7 * age) + 1.4;
            if(gender.equals("M")){
                childBodyFatLevel -= 3.6;
            }
            return childBodyFatLevel;
        }else{
            double adultBodyFatLevel = (1.39 * BMI) - (0.16 * age) - 9.0;
            if(gender.equals("M")){
                adultBodyFatLevel -= 10.34;
            }
            return adultBodyFatLevel;
        }
    }


}
