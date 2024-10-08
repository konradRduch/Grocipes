package com.grocipes_backend.grocipes.models.DTO;

public class NutrientDTO {

    private String name;
    private double amount;
    private double dailyValue;

    public NutrientDTO(String name, double amount, double dailyValue) {
        this.name = name;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(double dailyValue) {
        this.dailyValue = dailyValue;
    }
}
