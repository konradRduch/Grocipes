package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.models.Recipe;
import com.grocipes_backend.grocipes.models.RecipeProduct;
import com.grocipes_backend.grocipes.models.UnitRecipeProduct;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import com.grocipes_backend.grocipes.repositories.RecipeProductRepository;
import com.grocipes_backend.grocipes.repositories.RecipeRepository;
import com.grocipes_backend.grocipes.repositories.UnitRecipeProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
public class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecipeProductRepository recipeProductRepository;

    @Autowired
    private UnitRecipeProductRepository unitRecipeProductRepository;

    private Recipe testRecipe;
    private Product testProduct;
    private UnitRecipeProduct testUnit;

    @BeforeEach
    void setUp() {
        // Tworzymy przepis
        testRecipe = new Recipe();
        testRecipe.setTitle("Spaghetti Bolognese");
        testRecipe.setDescription("Klasyczny włoski przepis");
        testRecipe.setPreparation_method("Podsmaż mięso, dodaj sos pomidorowy...");
        testRecipe.setImage_url("spaghetti.jpg");
        recipeRepository.save(testRecipe);

        // Tworzymy produkt
        testProduct = new Product();
        testProduct.setName("Makaron spaghetti");
        testProduct.setWeight(500);
        testProduct.setPrice(6.5);
        testProduct.setCalories(200);
        testProduct.setImage_url("makaron.jpg");
        productRepository.save(testProduct);

        // Tworzymy jednostkę
        testUnit = new UnitRecipeProduct();
        testUnit.setName("gram");
        unitRecipeProductRepository.save(testUnit);

        // Tworzymy powiązanie RecipeProduct
        RecipeProduct rp = new RecipeProduct();
        rp.setRecipe(testRecipe);
        rp.setProduct(testProduct);
        rp.setPrice(6.5);
        rp.setQuantity(250);
        rp.setUnit(testUnit);
        recipeProductRepository.save(rp);
    }

    @Test
    void testFindByTitle() {
        List<Recipe> recipes = recipeRepository.findByTitle("Spaghetti Bolognese");

        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("Spaghetti Bolognese", recipes.get(0).getTitle());
    }

    @Test
    void testFindRecipeById() {
        Recipe recipe = recipeRepository.findRecipeById(testRecipe.getId());

        assertNotNull(recipe);
        assertEquals("Spaghetti Bolognese", recipe.getTitle());
    }

    @Test
    void testExistsByTitle() {
        assertTrue(recipeRepository.existsByTitle("Spaghetti Bolognese"));
        assertFalse(recipeRepository.existsByTitle("Pizza Margherita"));
    }

    @Test
    void testFindRecipes() {
        List<Object[]> results = recipeRepository.findRecipes();

        assertNotNull(results);
        assertFalse(results.isEmpty());

        Object[] row = results.get(0);
        assertEquals(testRecipe.getId(), row[0]); // ID przepisu
        assertEquals("Spaghetti Bolognese", row[1]); // Tytuł
        assertEquals("Makaron spaghetti", row[7]); // Nazwa produktu
        assertEquals("gram", row[13]); // Jednostka
    }
}
