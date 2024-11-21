package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product>findByName(String name);
    Product findProductById(Integer id);

    @Query("SELECT (p.id,p.name,p.weight, p.price,p.image_url,p.calories,p.unit.id,nfn.amount, nfn.dailyValue,n.name) " +
            "FROM NutritionFactNutrient nfn " +
            "JOIN Product p ON p.id = nfn.id.productId " +
            "JOIN Nutrient n ON n.id = nfn.id.nutrientId "
    )
    List<Object[]>findproducts();

}
