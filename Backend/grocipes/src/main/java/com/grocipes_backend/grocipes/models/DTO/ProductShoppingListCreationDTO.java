package com.grocipes_backend.grocipes.models.DTO;

public class ProductShoppingListCreationDTO {
    private Integer product_id;
    private Integer shop_list_id;
    private Double quantity;
    private Integer unit_id;
    private boolean done;

    public ProductShoppingListCreationDTO() {
    }

    public ProductShoppingListCreationDTO(Integer product_id, Integer shop_list_id, Double quantity, Integer unit_id, boolean done) {
        this.product_id = product_id;
        this.shop_list_id = shop_list_id;
        this.quantity = quantity;
        this.unit_id = unit_id;
        this.done = done;
    }

    public ProductShoppingListCreationDTO(Integer product_id, Double quantity, Integer unit_id, boolean done) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_id = unit_id;
        this.done = done;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getShop_list_id() {
        return shop_list_id;
    }

    public void setShop_list_id(Integer shop_list_id) {
        this.shop_list_id = shop_list_id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Integer unit_id) {
        this.unit_id = unit_id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
