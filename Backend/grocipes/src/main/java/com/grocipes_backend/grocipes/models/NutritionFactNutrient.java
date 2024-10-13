package com.grocipes_backend.grocipes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class NutritionFactNutrient {

    @EmbeddedId
    private NutritionFactNutrientId id;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "nutrition_fact_id")
    private Product product;

    @ManyToOne
    @MapsId("nutrientId")
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;

    // Relacja z NutritionFactNutrient
    @OneToMany(mappedBy = "nutrient", cascade = CascadeType.ALL)
    private List<DailyDemand>dailyDemands  = new ArrayList<>();

    private double amount;
    private double dailyValue;

    public NutritionFactNutrient() {
    }

    public NutritionFactNutrient(NutritionFactNutrientId id, Product product, Nutrient nutrient, double amount, double dailyValue) {
        this.id = id;
        this.product = product;
        this.nutrient = nutrient;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }

    public NutritionFactNutrient(NutritionFactNutrientId id, Product product, Nutrient nutrient, List<DailyDemand> dailyDemands, double amount, double dailyValue) {
        this.id = id;
        this.product = product;
        this.nutrient = nutrient;
        this.dailyDemands = dailyDemands;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }

    public NutritionFactNutrientId getId() {
        return id;
    }

    public void setId(NutritionFactNutrientId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(double dailyValue) {
        this.dailyValue = dailyValue;
    }

    public List<DailyDemand> getDailyDemands() {
        return dailyDemands;
    }

    public void setDailyDemands(List<DailyDemand> dailyDemands) {
        this.dailyDemands = dailyDemands;
    }
}
