package com.grocipes_backend.grocipes.models.DTO;

public class RegisterDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String image_url;
    private String role;
    private double weight;
    private double height;

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RegisterDTO(String name, String surname, String email, String password, String image_url, String role, double weight, double height) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.image_url = image_url;
        this.role = role;
        this.weight = weight;
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
