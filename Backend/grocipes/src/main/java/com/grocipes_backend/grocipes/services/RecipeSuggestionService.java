package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.*;
import com.grocipes_backend.grocipes.models.DTO.RecipeDTO;
import com.grocipes_backend.grocipes.repositories.DailyDemandRepository;
import com.grocipes_backend.grocipes.repositories.NutritionalGoalRepository;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RecipeSuggestionService {
    private final NutritionalGoalRepository nutritionalGoalRepository;
    private final RecipeRepository recipeRepository;
    private final DailyDemandRepository dailyDemandRepository;

    public RecipeSuggestionService(NutritionalGoalRepository nutritionalGoalRepository, RecipeRepository recipeRepository,
                                   DailyDemandRepository dailyDemandRepository) {
        this.nutritionalGoalRepository = nutritionalGoalRepository;
        this.recipeRepository = recipeRepository;
        this.dailyDemandRepository = dailyDemandRepository;
    }

    public List<RecipeDTO> getSuggestionsForUser(Integer userId) {
        // Pobierz aktywny cel użytkownika i dzienne zapotrzebowanie
        List<NutritionalGoal> goal = nutritionalGoalRepository.findNutritionalGoalsByUserEntityId(userId);

        List<DailyDemand> dailyDemands = dailyDemandRepository.findDailyDemandByNutritionalGoalId(goal.get(0).getId());

        double protein = dailyDemands.stream()
                .filter(n -> n.getNutrient().getId().getNutrientId().equals(5)) // ID dla białka
                .mapToDouble(DailyDemand::getQuantity)
                .findFirst()
                .orElse(0.0); // Pobierz wartość składnika odżywczego

        double carbs = dailyDemands.stream()
                .filter(n -> n.getNutrient().getId().getNutrientId().equals(4)) // ID dla węglowodanów
                .mapToDouble(DailyDemand::getQuantity)
                .findFirst()
                .orElse(0.0);


        double fat = dailyDemands.stream()
                .filter(n -> n.getNutrient().getId().getNutrientId().equals(1)) // ID dla tłuszczów
                .mapToDouble(DailyDemand::getQuantity)
                .findFirst()
                .orElse(0.0);// Pobierz wszystkie przepisy

        // Pobierz wszystkie przepisy
        List<Recipe> allRecipes = recipeRepository.findAll();

        // Filtrowanie przepisów na podstawie dziennego zapotrzebowania
        Map<String, List<Recipe>> recipesByType = allRecipes.stream()
               // .filter(recipe -> matchesDailyDemand(recipe, protein, carbs, fat))
                .collect(Collectors.groupingBy(Recipe::getTypeOfMeal));

        List<RecipeDTO> suggestions = new ArrayList<>();
        Random random = new Random();

        // Używamy tablicy do trzymania sumy
        double[] accumulated = new double[3]; // [0] - protein, [1] - carbs, [2] - fat

        // Lista przepisów, które spełniają wymagania
        List<Recipe> validRecipes = new ArrayList<>();

        // Dodajemy przepisy, dopóki nie osiągniemy zapotrzebowania
        recipesByType.forEach((type, recipes) -> {
            if (!recipes.isEmpty()) {
                // Wybieramy losowy przepis z danej grupy
                int randomIndex = random.nextInt(recipes.size());
                Recipe randomRecipe = recipes.get(randomIndex);

                // Oblicz wartość odżywczą tego przepisu
                double recipeProtein = calculateTotalNutrients(randomRecipe, 5); // Białko
                double recipeCarbs = calculateTotalNutrients(randomRecipe, 4); // Węglowodany
                double recipeFat = calculateTotalNutrients(randomRecipe, 1); // Tłuszcze

                // Sprawdzamy, czy możemy dodać przepis bez przekraczania zapotrzebowania
                if (accumulated[0] + recipeProtein <= protein && accumulated[1] + recipeCarbs <= carbs && accumulated[2] + recipeFat <= fat) {
                    validRecipes.add(randomRecipe);
                    accumulated[0] += recipeProtein;
                    accumulated[1] += recipeCarbs;
                    accumulated[2] += recipeFat;
                }
            }
        });

        // Mapujemy wybrane przepisy na DTO
        validRecipes.forEach(recipe -> suggestions.add(convertToDTO(recipe)));

        return suggestions;
    }

    private boolean matchesDailyDemand(Recipe recipe, double protein, double carbs, double fat) {
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;
        //carb id 4
        //protein id 5
        //fat id 1

        // Oblicz wartości odżywcze przepisu
        for (RecipeProduct rp : recipe.getRecipeProducts()) {
            Product product = rp.getProduct();
            double quantityInGrams = rp.getQuantity();

            totalProtein += product.getNutritionFacts().stream()
                    .filter(n -> n.getId().getNutrientId().equals(5)) // ID dla białka
                    .mapToDouble(n -> (n.getAmount() / 100) * quantityInGrams) // Uwzględnij wartość na 100g
                    .sum();

            totalCarbs += product.getNutritionFacts().stream()
                    .filter(n -> n.getId().getNutrientId().equals(4)) // ID dla węglowodanów
                    .mapToDouble(n -> (n.getAmount() / 100) * quantityInGrams) // Uwzględnij wartość na 100g
                    .sum();

            totalFat += product.getNutritionFacts().stream()
                    .filter(n -> n.getId().getNutrientId().equals(1)) // ID dla tłuszczów
                    .mapToDouble(n -> (n.getAmount() / 100) * quantityInGrams) // Uwzględnij wartość na 100g
                    .sum();
        }
        return totalProtein <= protein && totalCarbs <= carbs && totalFat <=fat;
        //        // Dopasowanie na podstawie zakresu tolerancji (np. +/- 10%)
        //        return  (Math.abs(protein - totalProtein) <= 0.1 * protein ) &&
        //                (Math.abs(carbs - totalCarbs) <= 0.1 * carbs ) &&
        //                (Math.abs(fat - totalFat) <= 0.1 * fat);

    }

    private RecipeDTO convertToDTO(Recipe recipe) {
        // Mapowanie encji na DTO
        return new RecipeDTO(recipe.getTitle(),recipe.getTypeOfMeal(), recipe.getDescription(),recipe.getPreparation_method(), recipe.getImage_url());
    }


    private double calculateTotalNutrients(Recipe recipe, int nutrientId) {
        double totalNutrient = 0;
        for (RecipeProduct rp : recipe.getRecipeProducts()) {
            Product product = rp.getProduct();
            double quantityInGrams = rp.getQuantity();

            totalNutrient += product.getNutritionFacts().stream()
                    .filter(n -> n.getId().getNutrientId().equals(nutrientId))
                    .mapToDouble(n -> (n.getAmount() / 100) * quantityInGrams)
                    .sum();
        }
        return totalNutrient;
    }










}
