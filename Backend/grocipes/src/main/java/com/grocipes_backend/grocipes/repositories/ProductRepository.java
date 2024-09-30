package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
