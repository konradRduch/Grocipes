package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.models.RecipeProduct;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import com.grocipes_backend.grocipes.repositories.RecipeProductRepository;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class RecipeProductRepositoryTest {

    @Autowired
    private RecipeProductRepository recipeProductRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    private Recipe testRecipe;
    private Product testProduct1;
    private Product testProduct2;

    @BeforeEach
    void setUp() {
        // Tworzymy przepis
        testRecipe = new Recipe();
        testRecipe.setTitle("Owsianka");
        testRecipe.setDescription("Zdrowa owsianka na śniadanie");
        recipeRepository.save(testRecipe);

        // Tworzymy dwa produkty
        testProduct1 = new Product();
        testProduct1.setName("Mleko");
        testProduct1.setPrice(5.0);
        productRepository.save(testProduct1);

        testProduct2 = new Product();
        testProduct2.setName("Płatki owsiane");
        testProduct2.setPrice(3.0);
        productRepository.save(testProduct2);

        // Przypisujemy produkty do przepisu
        RecipeProduct rp1 = new RecipeProduct();
        rp1.setRecipe(testRecipe);
        rp1.setProduct(testProduct1);
        rp1.setPrice(testProduct1.getPrice());
        recipeProductRepository.save(rp1);

        RecipeProduct rp2 = new RecipeProduct();
        rp2.setRecipe(testRecipe);
        rp2.setProduct(testProduct2);
        rp2.setPrice(testProduct2.getPrice());
        recipeProductRepository.save(rp2);
    }

    @Test
    void testFindRecipeProductsByRecipeId() {
        List<RecipeProduct> products = recipeProductRepository.findRecipeProductsByRecipeId(testRecipe.getId());

        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Mleko", products.get(0).getProduct().getName());
        assertEquals("Płatki owsiane", products.get(1).getProduct().getName());
    }

    @Test
    void testFindRecipeProductsByRecipeIdAndAggregate() {
        Double totalPrice = recipeProductRepository.findRecipeProductsByRecipeIdAndAggregate(testRecipe.getId());

        assertNotNull(totalPrice);
        assertEquals(8.0, totalPrice);
    }

    @Test
    void testDeleteByRecipeId() {
        recipeProductRepository.deleteByRecipeId(testRecipe.getId());
        List<RecipeProduct> products = recipeProductRepository.findRecipeProductsByRecipeId(testRecipe.getId());

        assertTrue(products.isEmpty());
    }

}
