package com.grocipes_backend.grocipes.models.DTO;

public class EstimateTimeDTO {

    private double currentWeight;
    private double targetWeight;
    private String typeOfGoal;

    public EstimateTimeDTO(double currentWeight, double targetWeight, String typeOfGoal) {
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
        this.typeOfGoal = typeOfGoal;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getTypeOfGoal() {
        return typeOfGoal;
    }

    public void setTypeOfGoal(String typeOfGoal) {
        this.typeOfGoal = typeOfGoal;
    }
}
