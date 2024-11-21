package com.grocipes_backend.grocipes.models.DTO;

import java.time.LocalDateTime;

public class CreateEatDeadlineDTO {
    private Integer userId;
    private Integer recipeId;
    private LocalDateTime eatingDate;
    private boolean done;
    private int rate;
    private String comment;
    private double totalPrice;

    public CreateEatDeadlineDTO() {
    }

    public CreateEatDeadlineDTO(Integer recipeId, LocalDateTime eatingDate, boolean done, int rate, String comment) {
        this.recipeId = recipeId;
        this.eatingDate = eatingDate;
        this.done = done;
        this.rate = rate;
        this.comment = comment;
    }

    public CreateEatDeadlineDTO(Integer recipeId, LocalDateTime eatingDate, boolean done, int rate, String comment, double totalPrice) {
        this.recipeId = recipeId;
        this.eatingDate = eatingDate;
        this.done = done;
        this.rate = rate;
        this.comment = comment;
        this.totalPrice = totalPrice;
    }

    public CreateEatDeadlineDTO(int rate) {
        this.rate = rate;
    }

    public CreateEatDeadlineDTO(String comment) {
        this.comment = comment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
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

}
