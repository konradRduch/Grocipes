package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDateTime;

public class EatDeadlineDTO {
    private Integer id;
    private Integer recipeId;
    private String title;
    private String typeOfMeal;
    private LocalDateTime eatingDate;
    private boolean done;
    private int rate;
    private String comment;
    private double totalPrice;

    public EatDeadlineDTO(Integer id, Integer recipeId, String title, String typeOfMeal, LocalDateTime eatingDate, boolean done, int rate, String comment,double totalPrice) {
        this.id = id;
        this.recipeId = recipeId;
        this.title = title;
        this.typeOfMeal = typeOfMeal;
        this.eatingDate = eatingDate;
        this.done = done;
        this.rate = rate;
        this.comment = comment;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEatingDate() {
        return eatingDate;
    }

    public void setEatingDate(LocalDateTime eatingDate) {
        this.eatingDate = eatingDate;
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

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
