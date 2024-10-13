package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UnitDailyDemand {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "unitDailyDemand", cascade = CascadeType.ALL)
    private List<DailyDemand> dailyDemand;

    public UnitDailyDemand() {
    }

    public UnitDailyDemand(Integer id, String name, List<DailyDemand> dailyDemand) {
        this.id = id;
        this.name = name;
        this.dailyDemand = dailyDemand;
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

    public List<DailyDemand> getDailyDemand() {
        return dailyDemand;
    }

    public void setDailyDemand(List<DailyDemand> dailyDemand) {
        this.dailyDemand = dailyDemand;
    }
}
