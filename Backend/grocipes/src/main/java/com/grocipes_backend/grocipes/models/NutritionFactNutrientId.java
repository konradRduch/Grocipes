package com.grocipes_backend.grocipes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NutritionFactNutrientId implements Serializable {

    @Column(name = "nutrition_fact_id")
    private Integer productId;

    @Column(name = "nutrient_id")
    private Integer nutrientId;

    public NutritionFactNutrientId() {
    }

    public NutritionFactNutrientId(Integer productId, Integer nutrientId) {
        this.productId = productId;
        this.nutrientId = nutrientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Integer nutrientId) {
        this.nutrientId = nutrientId;
    }
}
