package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.Nutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrient;
import com.grocipes_backend.grocipes.models.NutritionFactNutrientId;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.repositories.NutrientRepository;
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
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NutrientRepository nutrientRepository;

    @Autowired
    private NutritionFactNutrientRepository nutritionFactNutrientRepository;

    private Product testProduct;
    private Nutrient testNutrient;

    @BeforeEach
    void setUp() {
        // Tworzymy testowy produkt
        testProduct = new Product();
        testProduct.setName("Jabłko");
        testProduct.setWeight(200);
        testProduct.setPrice(2.5);
        testProduct.setImage_url("apple.jpg");
        testProduct.setCalories(100);
        productRepository.save(testProduct);

        // Tworzymy składnik odżywczy
        testNutrient = new Nutrient();
        testNutrient.setName("Witamina C");
        nutrientRepository.save(testNutrient);

        // Przypisujemy składnik odżywczy do produktu
        NutritionFactNutrient nfn = new NutritionFactNutrient();
        nfn.setId(new NutritionFactNutrientId(testProduct.getId(), testNutrient.getId()));
        nfn.setAmount(50.0);
        nfn.setDailyValue(60.0);
        nutritionFactNutrientRepository.save(nfn);
    }

    @Test
    void testFindProducts() {
        List<Object[]> products = productRepository.findproducts();

        assertNotNull(products);
        assertFalse(products.isEmpty());

        Object[] firstProduct = products.get(0);

        assertEquals(testProduct.getId(), firstProduct[0]);
        assertEquals("Jabłko", firstProduct[1]);
        assertEquals(200, firstProduct[2]);
        assertEquals(2.5, firstProduct[3]);
        assertEquals("apple.jpg", firstProduct[4]);
        assertEquals(100, firstProduct[5]);
        assertEquals(50.0, firstProduct[7]);  // amount
        assertEquals(60.0, firstProduct[8]);  // daily value
        assertEquals("Witamina C", firstProduct[9]);
    }
}
