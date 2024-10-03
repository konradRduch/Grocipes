package com.grocipes_backend.grocipes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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
    private Integer amount;
    private Integer dailyValue;

    public NutritionFactNutrient() {
    }

    public NutritionFactNutrient(NutritionFactNutrientId id, Product product, Nutrient nutrient, Integer amount, Integer dailyValue) {
        this.id = id;
        this.product = product;
        this.nutrient = nutrient;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDailyValue() {
        return dailyValue;
    }

    public void setDailyValue(Integer dailyValue) {
        this.dailyValue = dailyValue;
    }
}
