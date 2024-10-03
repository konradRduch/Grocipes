package com.grocipes_backend.grocipes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Nutrient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // Relacja z NutritionFactNutrient
    @OneToMany(mappedBy = "nutrient", cascade = CascadeType.ALL)
    private List<NutritionFactNutrient> nutritionFactss = new ArrayList<>();

    public Nutrient() {
    }

    public Nutrient(Integer id, String name, List<NutritionFactNutrient> nutritionFacts) {
        this.id = id;
        this.name = name;
        this.nutritionFactss = nutritionFacts;
    }

    public List<NutritionFactNutrient> getNutritionFacts() {
        return nutritionFactss;
    }

    public void setNutritionFacts(List<NutritionFactNutrient> nutritionFacts) {
        this.nutritionFactss = nutritionFacts;
    }

    public Nutrient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
