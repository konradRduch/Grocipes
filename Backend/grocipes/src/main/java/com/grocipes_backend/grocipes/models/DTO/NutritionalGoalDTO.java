package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDate;

public class NutritionalGoalDTO {
    private Integer id;
    private String name;
    private String typeOfGoal;
    private LocalDate goalStartDate;
    private LocalDate goalEndDate;
    private boolean isActive;
    private double targetWeight;
    private double targetAbdominalCircumference;
    private double targetBodyFatLevel;
    private int progress;

    public NutritionalGoalDTO() {
    }


    public NutritionalGoalDTO(Integer id, String name, String typeOfGoal, boolean isActive, double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel, int progress) {
        this.id = id;
        this.name = name;
        this.typeOfGoal = typeOfGoal;
        this.isActive = isActive;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
        this.progress = progress;
    }

    public NutritionalGoalDTO(Integer id, String name, String typeOfGoal, LocalDate goalStartDate, LocalDate goalEndDate, boolean isActive, double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel, int progress) {
        this.id = id;
        this.name = name;
        this.typeOfGoal = typeOfGoal;
        this.goalStartDate = goalStartDate;
        this.goalEndDate = goalEndDate;
        this.isActive = isActive;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
        this.progress = progress;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfGoal() {
        return typeOfGoal;
    }

    public void setTypeOfGoal(String typeOfGoal) {
        this.typeOfGoal = typeOfGoal;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public LocalDate getGoalStartDate() {
        return goalStartDate;
    }

    public void setGoalStartDate(LocalDate goalStartDate) {
        this.goalStartDate = goalStartDate;
    }

    public LocalDate getGoalEndDate() {
        return goalEndDate;
    }

    public void setGoalEndDate(LocalDate goalEndDate) {
        this.goalEndDate = goalEndDate;
    }

}
