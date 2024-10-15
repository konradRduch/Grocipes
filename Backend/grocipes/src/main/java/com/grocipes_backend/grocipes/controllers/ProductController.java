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

    @GetMapping("/{id}")
    public GetProductsDTO getProduct(@PathVariable Integer id){
        List<GetProductsDTO>productsDTOS = productService.getProducts();

        // Znajdź produkt o podanym ID
        GetProductsDTO foundProduct = productsDTOS.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null); // Zwróć null, jeśli nie znaleziono  
        return foundProduct;
    }

    @GetMapping("")
    public List<GetProductsDTO>getProducts(){
        return productService.getProducts();
        //return productService.getAllProducts();
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody ProductCreationDTO request){
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
            nutritionFactNutrientService.addNutrionFactNutrient(nutritionFactNutrientDTO.get(i));
        }
       return ResponseEntity.ok().build();
    }
    //usuwanie
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        // Najpierw usuń powiązane rekordy NutritionFactNutrient
        nutritionFactNutrientService.deleteNutritionFactsByProductId(id);

        // Następnie usuń produkt
        productService.deleteProductById(id);

        return ResponseEntity.ok().build();
    }

    //edytowanie
    @PatchMapping("edit/{id}")
    public ResponseEntity<Void> editProduct(@PathVariable Integer id, @RequestBody ProductCreationDTO updatedProduct) {
        // Znajdź istniejący produkt po jego ID
        Product product = productService.findById(id);

        // Aktualizuj dane produktu
        ProductDTO productDTO = updatedProduct.getProductDTO();
        product.setId(id);
        product.setName(productDTO.getName());
        product.setWeight(productDTO.getWeight());
        product.setPrice(productDTO.getPrice());
        product.setImage_url(productDTO.getImage_url());
        product.setCalories(productDTO.getCalories());
        productService.save(product);

        // Usuwanie istniejących wartości odżywczych dla danego produktu
        nutritionFactNutrientService.deleteNutritionFactsByProductId(id);

        // Dodawanie nowych wartości odżywczych
        for (NutritionFactNutrientDTO nutrientDTO : updatedProduct.getNutritionFactNutrientDTO()) {
            nutrientDTO.setProductId(id);
            nutritionFactNutrientService.addNutrionFactNutrient(nutrientDTO);
        }

        return ResponseEntity.ok().build();
    }




}
