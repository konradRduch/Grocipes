package com.grocipes_backend.grocipes.models.DTO;

import com.grocipes_backend.grocipes.models.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class BodyMeasurementsDTO {
    private Integer id;
    private double weight;
    private double height;
    private double abdominal_circumference;
    private double body_fat_leve;
    private String physical_activity;
    private LocalDateTime measurement_date;
    private Integer userId;

    public BodyMeasurementsDTO(Integer id, double weight, double height, double abdominal_circumference, double body_fat_leve, String physical_activity, LocalDateTime measurement_date, Integer userId) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.abdominal_circumference = abdominal_circumference;
        this.body_fat_leve = body_fat_leve;
        this.physical_activity = physical_activity;
        this.measurement_date = measurement_date;
        this.userId = userId;
    }

    public BodyMeasurementsDTO(double weight, double height, double abdominal_circumference, double body_fat_leve, String physical_activity, LocalDateTime measurement_date, Integer userId) {
        this.weight = weight;
        this.height = height;
        this.abdominal_circumference = abdominal_circumference;
        this.body_fat_leve = body_fat_leve;
        this.physical_activity = physical_activity;
        this.measurement_date = measurement_date;
        this.userId = userId;
    }

    public BodyMeasurementsDTO() {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
