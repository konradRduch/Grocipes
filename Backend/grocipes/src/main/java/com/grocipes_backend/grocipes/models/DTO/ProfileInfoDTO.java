package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProfileInfoDTO {
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private String gender;
    private double weight;
    private double height;
    private double abdominal_circumference;
    private double body_fat_leve;
    private String physical_activity;
    private LocalDateTime measurement_date;

    public ProfileInfoDTO(String name, String surname, String email, LocalDate birthday, String gender, double weight, double height, double abdominal_circumference, double body_fat_leve, String physical_activity, LocalDateTime measurement_date) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.abdominal_circumference = abdominal_circumference;
        this.body_fat_leve = body_fat_leve;
        this.physical_activity = physical_activity;
        this.measurement_date = measurement_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
