package com.grocipes_backend.grocipes.models.DTO;

public class ProgressDTO {
    private double startWeight;
    private double startAbdominalCircumference;
    private double startBodyFatLevel;
    private double currentWeight;
    private double currentAbdominalCircumference;
    private double currentBodyFatLevel;
    private double targetWeight;
    private double targetAbdominalCircumference;
    private double targetBodyFatLevel;

    public ProgressDTO(double startWeight, double startAbdominalCircumference, double startBodyFatLevel, double currentWeight, double currentAbdominalCircumference, double currentBodyFatLevel, double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel) {
        this.startWeight = startWeight;
        this.startAbdominalCircumference = startAbdominalCircumference;
        this.startBodyFatLevel = startBodyFatLevel;
        this.currentWeight = currentWeight;
        this.currentAbdominalCircumference = currentAbdominalCircumference;
        this.currentBodyFatLevel = currentBodyFatLevel;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
    }

    public ProgressDTO(double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel) {
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
    }

    public ProgressDTO() {
    }

    public double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

    public double getStartAbdominalCircumference() {
        return startAbdominalCircumference;
    }

    public void setStartAbdominalCircumference(double startAbdominalCircumference) {
        this.startAbdominalCircumference = startAbdominalCircumference;
    }

    public double getStartBodyFatLevel() {
        return startBodyFatLevel;
    }

    public void setStartBodyFatLevel(double startBodyFatLevel) {
        this.startBodyFatLevel = startBodyFatLevel;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getCurrentAbdominalCircumference() {
        return currentAbdominalCircumference;
    }

    public void setCurrentAbdominalCircumference(double currentAbdominalCircumference) {
        this.currentAbdominalCircumference = currentAbdominalCircumference;
    }

    public double getCurrentBodyFatLevel() {
        return currentBodyFatLevel;
    }

    public void setCurrentBodyFatLevel(double currentBodyFatLevel) {
        this.currentBodyFatLevel = currentBodyFatLevel;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public double getTargetAbdominalCircumference() {
        return targetAbdominalCircumference;
    }

    public void setTargetAbdominalCircumference(double targetAbdominalCircumference) {
        this.targetAbdominalCircumference = targetAbdominalCircumference;
    }

    public double getTargetBodyFatLevel() {
        return targetBodyFatLevel;
    }

    public void setTargetBodyFatLevel(double targetBodyFatLevel) {
        this.targetBodyFatLevel = targetBodyFatLevel;
    }
}
