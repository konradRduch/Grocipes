package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.ProfileInfoDTO;
import com.grocipes_backend.grocipes.repositories.NutritionalGoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.abs;

@Service
public class NutritionalGoalService {

    private final NutritionalGoalRepository nutritionalGoalRepository;

    public NutritionalGoalService(NutritionalGoalRepository nutritionalGoalRepository) {
        this.nutritionalGoalRepository = nutritionalGoalRepository;
    }

    public void cos(ProfileInfoDTO profileInfoDTO){
        LocalDate birthDate = profileInfoDTO.getBirthday();
        LocalDate currentDate = LocalDate.now();
        int age = (int) ChronoUnit.YEARS.between(birthDate, currentDate);
        double BMR = calculateBMR(profileInfoDTO.getGender(), profileInfoDTO.getWeight(),profileInfoDTO.getHeight(),age);
        double TDEE = calculateAverageTDEE(BMR, "High active");

        calculateDailyNutrientBreakdown(TDEE, "weight gain");

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


    private double estimatedTimeToAchieveGoal(double currentWeight, double targetWeight, String typeOfGoal){
    //return time in weeks
        if(typeOfGoal.equals("weight loss")){
            return abs(currentWeight - targetWeight) / 0.75 ;
        }else if(typeOfGoal.equals("weight gain")){
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






}
