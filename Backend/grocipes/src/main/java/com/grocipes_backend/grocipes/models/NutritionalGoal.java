package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class NutritionalGoal {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean goal_completed;
    private double weight;
    private double abdominal_circumference;
    private double body_fat_level;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @OneToMany(mappedBy = "nutritionalGoal", cascade = CascadeType.ALL)
    private List<DailyDemand> dailyDemandList;


    public NutritionalGoal(Integer id, String name, boolean goal_completed, double weight, double abdominal_circumference, double body_fat_level) {
        this.id = id;
        this.name = name;
        this.goal_completed = goal_completed;
        this.weight = weight;
        this.abdominal_circumference = abdominal_circumference;
        this.body_fat_level = body_fat_level;
    }

    public NutritionalGoal(Integer id, String name, boolean goal_completed, double weight, double abdominal_circumference, double body_fat_level, UserEntity userEntity, List<DailyDemand> dailyDemandList) {
        this.id = id;
        this.name = name;
        this.goal_completed = goal_completed;
        this.weight = weight;
        this.abdominal_circumference = abdominal_circumference;
        this.body_fat_level = body_fat_level;
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

    public boolean isGoal_completed() {
        return goal_completed;
    }

    public void setGoal_completed(boolean goal_completed) {
        this.goal_completed = goal_completed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAbdominal_circumference() {
        return abdominal_circumference;
    }

    public void setAbdominal_circumference(double abdominal_circumference) {
        this.abdominal_circumference = abdominal_circumference;
    }

    public double getBody_fat_level() {
        return body_fat_level;
    }

    public void setBody_fat_level(double body_fat_level) {
        this.body_fat_level = body_fat_level;
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
}
