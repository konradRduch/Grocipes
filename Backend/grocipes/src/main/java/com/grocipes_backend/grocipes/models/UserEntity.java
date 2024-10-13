package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate birthday;
    private String gender;


    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<NutritionalGoal> nutritionGoal;

    //@ManyToOne
    //@JoinColumn(name = "eat_schedule_id")
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<NutritionSchedule> eatSchedule;

    //@ManyToOne
    //@JoinColumn(name = "shop_schedule_id")
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<ShoppingSchedule> shopSchedule;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "user_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe>favouriteRecipes;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BodyMeasurements> bodyMeasurements = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Image>images;

    public UserEntity () {
    }

    public UserEntity (Integer id, String email, String password, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity (String email, String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity (String email, String password, Collection<GrantedAuthority> grantedAuthorities) {
        this.email = email;
        this.password = password;
        this.roles = new ArrayList<>();
    }


    public UserEntity(Integer id, String name, String surname, String email, String password, LocalDate birthday, String gender, List<NutritionalGoal> nutritionGoal, List<NutritionSchedule> eatSchedule, List<ShoppingSchedule> shopSchedule, List<Role> roles, List<Recipe> favouriteRecipes, List<BodyMeasurements> bodyMeasurements) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.nutritionGoal = nutritionGoal;
        this.eatSchedule = eatSchedule;
        this.shopSchedule = shopSchedule;
        this.roles = roles;
        this.favouriteRecipes = favouriteRecipes;
        this.bodyMeasurements = bodyMeasurements;
    }

    public UserEntity(Integer id, String name, String surname, String email, String password, LocalDate birthday, String gender, List<NutritionalGoal> nutritionGoal, List<NutritionSchedule> eatSchedule, List<ShoppingSchedule> shopSchedule, List<Role> roles, List<Recipe> favouriteRecipes, List<BodyMeasurements> bodyMeasurements, List<Image> images) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.nutritionGoal = nutritionGoal;
        this.eatSchedule = eatSchedule;
        this.shopSchedule = shopSchedule;
        this.roles = roles;
        this.favouriteRecipes = favouriteRecipes;
        this.bodyMeasurements = bodyMeasurements;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public List<NutritionalGoal> getNutritionGoal() {
        return nutritionGoal;
    }

    public void setNutritionGoal(List<NutritionalGoal> nutritionGoal) {
        this.nutritionGoal = nutritionGoal;
    }

    public List<NutritionSchedule> getEatSchedule() {
        return eatSchedule;
    }

    public void setEatSchedule(List<NutritionSchedule> eatSchedule) {
        this.eatSchedule = eatSchedule;
    }

    public List<ShoppingSchedule> getShopSchedule() {
        return shopSchedule;
    }

    public void setShopSchedule(List<ShoppingSchedule> shopSchedule) {
        this.shopSchedule = shopSchedule;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
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

    public List<BodyMeasurements> getBodyMeasurements() {
        return bodyMeasurements;
    }

    public void setBodyMeasurements(List<BodyMeasurements> bodyMeasurements) {
        this.bodyMeasurements = bodyMeasurements;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
