package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class NutritionalGoal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String typeOfGoal;
    private double targetWeight;
    private double targetAbdominalCircumference;
    private double targetBodyFatLevel;
    private LocalDate goalStartDate;
    private LocalDate goalEndDate;
    private int progress;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @OneToMany(mappedBy = "nutritionalGoal", cascade = CascadeType.ALL)
    private List<DailyDemand> dailyDemandList;


    public NutritionalGoal(Integer id, String name, double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel) {
        this.id = id;
        this.name = name;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
    }

    public NutritionalGoal(Integer id, String name, double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel, UserEntity userEntity, List<DailyDemand> dailyDemandList) {
        this.id = id;
        this.name = name;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
        this.userEntity = userEntity;
        this.dailyDemandList = dailyDemandList;
    }

    public NutritionalGoal(Integer id, String name, String typeOfGoal, double targetWeight, double targetAbdominalCircumference, double targetBodyFatLevel, LocalDate goalStartDate, LocalDate goalEndDate, int progress, boolean isActive, UserEntity userEntity, List<DailyDemand> dailyDemandList) {
        this.id = id;
        this.name = name;
        this.typeOfGoal = typeOfGoal;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
        this.goalStartDate = goalStartDate;
        this.goalEndDate = goalEndDate;
        this.progress = progress;
        this.isActive = isActive;
        this.userEntity = userEntity;
        this.dailyDemandList = dailyDemandList;
    }

    public NutritionalGoal() {
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<DailyDemand> getDailyDemandList() {
        return dailyDemandList;
    }

    public void setDailyDemandList(List<DailyDemand> dailyDemandList) {
        this.dailyDemandList = dailyDemandList;
    }

    public String getTypeOfGoal() {
        return typeOfGoal;
    }

    public void setTypeOfGoal(String typeOfGoal) {
        this.typeOfGoal = typeOfGoal;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
