package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product>getAllProducts(){
       return productRepository.findAll();
    }

    public Optional<Product>addProduct(Product product){
        return Optional.of(productRepository.save(product));
    }

}
