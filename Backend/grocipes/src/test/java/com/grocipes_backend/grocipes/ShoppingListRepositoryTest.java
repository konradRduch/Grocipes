package com.grocipes_backend.grocipes;
import static org.junit.jupiter.api.Assertions.*;

import com.grocipes_backend.grocipes.models.*;
import com.grocipes_backend.grocipes.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
public class ShoppingListRepositoryTest {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductShoppingListRepository productShoppingListRepository;

    @Autowired
    private UnitProductShoppingListRepository unitProductShoppingListRepository;

    @Autowired
    private ShoppingScheduleRepository shoppingScheduleRepository;

    private ShoppingList testShoppingList;
    private ShoppingSchedule testSchedule;
    private Product testProduct;
    private UnitProductShoppingList testUnit;

    @BeforeEach
    void setUp() {
        // Tworzymy harmonogram zakupów
        testSchedule = new ShoppingSchedule();
        shoppingScheduleRepository.save(testSchedule);

        // Tworzymy listę zakupów
        testShoppingList = new ShoppingList();
        testShoppingList.setName("Zakupy na weekend");
        testShoppingList.setShopping_date(LocalDateTime.now());
        testShoppingList.setCardColor(1);
        testShoppingList.setShoppingList(testSchedule);
        shoppingListRepository.save(testShoppingList);

        // Tworzymy produkt
        testProduct = new Product();
        testProduct.setName("Chleb");
        testProduct.setWeight(500);
        testProduct.setPrice(4.0);
        testProduct.setCalories(250);
        testProduct.setImage_url("chleb.jpg");
        productRepository.save(testProduct);

        // Tworzymy jednostkę
        testUnit = new UnitProductShoppingList();
        testUnit.setName("sztuka");
        unitProductShoppingListRepository.save(testUnit);

        // Tworzymy powiązanie ProductShoppingList
        ProductShoppingList psl = new ProductShoppingList();
        psl.setProduct(testProduct);
        psl.setShoppingList(testShoppingList);
        psl.setQuantity(2);
        psl.setDone(false);
        psl.setUnitProductShoppingList(testUnit);
        productShoppingListRepository.save(psl);
    }

    @Test
    void testFindShoppingListById() {
        ShoppingList foundList = shoppingListRepository.findShoppingListById(testShoppingList.getId());

        assertNotNull(foundList);
        assertEquals("Zakupy na weekend", foundList.getName());
    }

    @Test
    void testFindShoppingLists() {
        List<Object[]> results = shoppingListRepository.findShoppingLists();

        assertNotNull(results);
        assertFalse(results.isEmpty());

        Object[] row = results.get(0);
        assertEquals(testProduct.getId(), row[0]); // ID produktu
        assertEquals("Chleb", row[3]); // Nazwa produktu
        assertEquals("sztuka", row[14]); // Jednostka
    }

    @Test
    void testFindShoppingListsWithProductsByUserId() {
        List<Object[]> results = shoppingListRepository.findShoppingListsWithProductsByUserId(testSchedule.getId());

        assertNotNull(results);
        assertFalse(results.isEmpty());

        Object[] row = results.get(0);
        assertEquals(testShoppingList.getId(), ((ShoppingList) row[0]).getId()); // ID listy zakupów
        assertEquals(testSchedule.getId(), ((ShoppingSchedule) row[1]).getId()); // ID harmonogramu
    }

    @Test
    void testFindShoppingListByShoppingSchedule() {
        List<ShoppingList> results = shoppingListRepository.findShoppingListByShoppingList(testSchedule);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("Zakupy na weekend", results.get(0).getName());
    }
}
