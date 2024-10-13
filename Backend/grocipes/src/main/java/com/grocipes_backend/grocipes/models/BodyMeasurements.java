package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BodyMeasurements {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double weight;
    private double height;
    private double abdominal_circumference;
    private double body_fat_leve;
    private String physical_activity;
    private LocalDateTime measurement_date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    public BodyMeasurements() {
    }

    public BodyMeasurements(Integer id, double weight, double height, double abdominal_circumference, double body_fat_leve, String physical_activity, LocalDateTime measurement_date, UserEntity userEntity) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.abdominal_circumference = abdominal_circumference;
        this.body_fat_leve = body_fat_leve;
        this.physical_activity = physical_activity;
        this.measurement_date = measurement_date;
        this.userEntity = userEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getAbdominal_circumference() {
        return abdominal_circumference;
    }

    public void setAbdominal_circumference(double abdominal_circumference) {
        this.abdominal_circumference = abdominal_circumference;
    }

    public double getBody_fat_leve() {
        return body_fat_leve;
    }

    public void setBody_fat_leve(double body_fat_leve) {
        this.body_fat_leve = body_fat_leve;
    }

    public String getPhysical_activity() {
        return physical_activity;
    }

    public void setPhysical_activity(String physical_activity) {
        this.physical_activity = physical_activity;
    }

    public LocalDateTime getMeasurement_date() {
        return measurement_date;
    }

    public void setMeasurement_date(LocalDateTime measurement_date) {
        this.measurement_date = measurement_date;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
