package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.GetProductsDTO;
import com.grocipes_backend.grocipes.models.DTO.NutritionFactNutrientDTO;
import com.grocipes_backend.grocipes.models.DTO.ProductCreationDTO;
import com.grocipes_backend.grocipes.models.DTO.ProductDTO;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.services.NutritionFactNutrientService;
import com.grocipes_backend.grocipes.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final NutritionFactNutrientService nutritionFactNutrientService;

    public ProductController(ProductService productService, NutritionFactNutrientService nutritionFactNutrientService) {
        this.productService = productService;
        this.nutritionFactNutrientService = nutritionFactNutrientService;
    }

    @GetMapping("")
    public List<GetProductsDTO>getProducts(){
        return productService.getProducts();
        //return productService.getAllProducts();
    }
    @PostMapping("/add")
    public String addProduct(@RequestBody ProductCreationDTO request){
        ProductDTO productDTO = request.getProductDTO();
        List<NutritionFactNutrientDTO> nutritionFactNutrientDTO = request.getNutritionFactNutrientDTO();
        //Product
        productService.addProduct(productDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

        Integer productId = productService.getProductIdByName(productDTO.getName());
        for(int i = 0;i<nutritionFactNutrientDTO.size() ;i++) {
        nutritionFactNutrientDTO.get(i).setProductId(productId);
        }

        //Nutrient
        for(int i = 0;i<nutritionFactNutrientDTO.size() ;i++){
            nutritionFactNutrientService.addNutrionFactNutrient(nutritionFactNutrientDTO.get(i))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

       return "redirect:/products";
    }




}
