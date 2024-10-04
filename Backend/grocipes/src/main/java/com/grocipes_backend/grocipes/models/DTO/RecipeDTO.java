package com.grocipes_backend.grocipes.models.DTO;

public class RecipeDTO {

    private String title;
    private String description;
    private String preparation_method;
    private String image_url;

    public RecipeDTO(String title, String description, String preparation_method, String image_url) {
        this.title = title;
        this.description = description;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparation_method() {
        return preparation_method;
    }

    public void setPreparation_method(String preparation_method) {
        this.preparation_method = preparation_method;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
