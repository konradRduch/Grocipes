package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

@Entity
public class DailyDemand {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double quantity;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "nutrition_fact_id", referencedColumnName = "nutrition_fact_id"),
            @JoinColumn(name = "nutrient_id", referencedColumnName = "nutrient_id")
    })
    private NutritionFactNutrient nutrient;
    @ManyToOne
    @JoinColumn(name="nutritional_goal_id")
    private NutritionalGoal nutritionalGoal;
    @ManyToOne
    @JoinColumn(name="unit_daily_demand_id")
    private UnitDailyDemand unitDailyDemand;

    public DailyDemand() {
    }

    public DailyDemand(Integer id, double quantity, NutritionFactNutrient nutrient, NutritionalGoal nutritionalGoal, UnitDailyDemand unitDailyDemand) {
        this.id = id;
        this.quantity = quantity;
        this.nutrient = nutrient;
        this.nutritionalGoal = nutritionalGoal;
        this.unitDailyDemand = unitDailyDemand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public NutritionFactNutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(NutritionFactNutrient nutrient) {
        this.nutrient = nutrient;
    }

    public NutritionalGoal getNutritionalGoal() {
        return nutritionalGoal;
    }

    public void setNutritionalGoal(NutritionalGoal nutritionalGoal) {
        this.nutritionalGoal = nutritionalGoal;
    }

    public UnitDailyDemand getUnitDailyDemand() {
        return unitDailyDemand;
    }

    public void setUnitDailyDemand(UnitDailyDemand unitDailyDemand) {
        this.unitDailyDemand = unitDailyDemand;
    }
}
