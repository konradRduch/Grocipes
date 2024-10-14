package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "data", columnDefinition = "BLOB")
    private byte[] data;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;
    @ManyToOne
    @JoinColumn(name="eat_deadline_id")
    private EatDeadline eatDeadline;

    public Image() {
    }

    public Image(Integer id, String name, String type, byte[] data, Product product, UserEntity user, Recipe recipe, EatDeadline eatDeadline) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.product = product;
        this.user = user;
        this.recipe = recipe;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public EatDeadline getEatDeadline() {
        return eatDeadline;
    }

    public void setEatDeadline(EatDeadline eatDeadline) {
        this.eatDeadline = eatDeadline;
    }
}
