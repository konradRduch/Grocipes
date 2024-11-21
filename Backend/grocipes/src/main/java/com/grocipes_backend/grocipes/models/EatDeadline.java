package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class EatDeadline {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime eating_date;
    private boolean done;
    private int rate;
    private String comment;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name="nutrition_schedule_id")
    private NutritionSchedule nutritionSchedule;
    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
    @OneToMany(mappedBy = "eatDeadline", cascade = CascadeType.ALL)
    private List<Image>image;
    public EatDeadline() {
    }

    public EatDeadline(Integer id, LocalDateTime eating_date, boolean done, int rate, String comment, double totalPrice, NutritionSchedule nutritionSchedule, Recipe recipe, List<Image> image) {
        this.id = id;
        this.eating_date = eating_date;
        this.done = done;
        this.rate = rate;
        this.comment = comment;
        this.totalPrice = totalPrice;
        this.nutritionSchedule = nutritionSchedule;
        this.recipe = recipe;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEating_date() {
        return eating_date;
    }

    public void setEating_date(LocalDateTime eating_date) {
        this.eating_date = eating_date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public NutritionSchedule getNutritionSchedule() {
        return nutritionSchedule;
    }

    public void setNutritionSchedule(NutritionSchedule nutritionSchedule) {
        this.nutritionSchedule = nutritionSchedule;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
