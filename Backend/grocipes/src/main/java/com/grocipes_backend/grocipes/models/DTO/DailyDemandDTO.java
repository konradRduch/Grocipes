package com.grocipes_backend.grocipes.models.DTO;

public class DailyDemandDTO {

    double protein;
    double carbs;
    double fats;
    double calories;

    public DailyDemandDTO() {
    }

    public DailyDemandDTO(double protein, double carbs, double fats, double calories) {
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
