package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.NutritionFactNutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrientId;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.repositories.NutritionFactNutrientRepository;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class NutritionFactNutrientRepositoryTest {


    @Autowired
    private NutritionFactNutrientRepository nutritionFactNutrientRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;
    private NutritionFactNutrient testNutrient1;
    private NutritionFactNutrient testNutrient2;

    @BeforeEach
    void setUp() {
        // Tworzenie produktu testowego
        testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setCalories(200);
        productRepository.save(testProduct);

        // Tworzenie składników odżywczych dla produktu
        testNutrient1 = new NutritionFactNutrient();
        testNutrient1.setId(new NutritionFactNutrientId(testProduct.getId(), 1)); // Nutrient ID = 1
        testNutrient1.setAmount(10.0);
        nutritionFactNutrientRepository.save(testNutrient1);

        testNutrient2 = new NutritionFactNutrient();
        testNutrient2.setId(new NutritionFactNutrientId(testProduct.getId(), 2)); // Nutrient ID = 2
        testNutrient2.setAmount(5.0);
        nutritionFactNutrientRepository.save(testNutrient2);
    }

    @Test
    void testFindByNutrientId() {
        List<NutritionFactNutrient> results = nutritionFactNutrientRepository.findByNutrientId(1);
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(10.0, results.get(0).getAmount());
    }

    @Test
    void testDeleteByProductId() {
        nutritionFactNutrientRepository.deleteByProductId(testProduct.getId());

        List<NutritionFactNutrient> results = nutritionFactNutrientRepository.findByNutrientId(1);
        assertTrue(results.isEmpty());

        results = nutritionFactNutrientRepository.findByNutrientId(2);
        assertTrue(results.isEmpty());
    }

}
