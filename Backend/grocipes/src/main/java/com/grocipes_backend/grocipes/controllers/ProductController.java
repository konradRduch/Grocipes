package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("")
    public List<Product>getProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return productService.addProduct(product).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }




}
