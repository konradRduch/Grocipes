package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ShoppingSchedule {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    private List<ShoppingList> shoppingList;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public ShoppingSchedule() {
    }

    public ShoppingSchedule(Integer id, String name, List<ShoppingList> shoppingList, UserEntity userEntity) {
        this.id = id;
        this.name = name;
        this.shoppingList = shoppingList;
        this.userEntity = userEntity;
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

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
