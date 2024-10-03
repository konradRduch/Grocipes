package com.grocipes_backend.grocipes.models.DTO;

import java.util.List;

public class ProductCreationDTO {
    private ProductDTO productDTO;
    private List<NutritionFactNutrientDTO> nutritionFactNutrientDTO;

    public ProductCreationDTO(ProductDTO productDTO, List<NutritionFactNutrientDTO> nutritionFactNutrientDTO) {
        this.productDTO = productDTO;
        this.nutritionFactNutrientDTO = nutritionFactNutrientDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public List<NutritionFactNutrientDTO> getNutritionFactNutrientDTO() {
        return nutritionFactNutrientDTO;
    }

    public void setNutritionFactNutrientDTO(List<NutritionFactNutrientDTO> nutritionFactNutrientDTO) {
        this.nutritionFactNutrientDTO = nutritionFactNutrientDTO;
    }
}
