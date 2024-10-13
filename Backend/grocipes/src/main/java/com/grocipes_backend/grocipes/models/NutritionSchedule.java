package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class NutritionSchedule {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @OneToMany(mappedBy = "nutritionSchedule", cascade = CascadeType.ALL)
    private List<EatDeadline> eatDeadline;

    public NutritionSchedule() {
    }

    public NutritionSchedule(Integer id, String name, UserEntity userEntity, List<EatDeadline> eatDeadline) {
        this.id = id;
        this.name = name;
        this.userEntity = userEntity;
        this.eatDeadline = eatDeadline;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<EatDeadline> getEatDeadline() {
        return eatDeadline;
    }

    public void setEatDeadline(List<EatDeadline> eatDeadline) {
        this.eatDeadline = eatDeadline;
    }
}
