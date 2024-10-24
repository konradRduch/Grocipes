package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.*;
import com.grocipes_backend.grocipes.models.ProductShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.security.JWTGenerator;
import com.grocipes_backend.grocipes.services.ProductShoppingListService;
import com.grocipes_backend.grocipes.services.ShoppingListService;
import com.grocipes_backend.grocipes.services.ShoppingScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {

    private ShoppingListService shoppingListService;
    private ShoppingScheduleService shoppingScheduleService;
    private JWTGenerator jwtGenerator;
    private ProductShoppingListService productShoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService, ShoppingScheduleService shoppingScheduleService, JWTGenerator jwtGenerator, ProductShoppingListService productShoppingListService) {
        this.shoppingListService = shoppingListService;
        this.shoppingScheduleService = shoppingScheduleService;
        this.jwtGenerator = jwtGenerator;
        this.productShoppingListService = productShoppingListService;
    }

    @GetMapping("getUserShoppingSchedules")
    public List<ShoppingScheduleDTO>getAllShoppingList(HttpServletRequest request){
        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);
            List<ShoppingScheduleDTO> shoppingSchedules = shoppingScheduleService.findByUserId(userId);
            ShoppingScheduleDTO shoppingSchedule = shoppingSchedules.stream()
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            return shoppingSchedules;
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }
    @GetMapping("/{id}")
    public ShoppingListDTO getShoppingList(@PathVariable Integer id){
        return shoppingListService.findShoppingListDTOById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addShoppingList(@RequestBody ShoppingListCreationDTO shoppingListCreationDTO ,HttpServletRequest request){

        String token = request.getHeader("Authorization"); // Odczytaj token z nagłówka

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Integer userId = jwtGenerator.getUserIdFromJWT(token);

            shoppingListCreationDTO.setUserId(userId);
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setName(shoppingListCreationDTO.getName());
            shoppingList.setShopping_date(shoppingListCreationDTO.getShopping_date());
            shoppingList.setCardColor(shoppingListCreationDTO.getCardColor());
            shoppingList.setLikedList(shoppingListCreationDTO.isLikedList());

            ShoppingSchedule shoppingSchedule = shoppingScheduleService.findShoppingScheduleByUserId(shoppingListCreationDTO.getUserId());
            shoppingList.setShoppingList(shoppingSchedule);
            shoppingListService.save(shoppingList);

            List<ProductShoppingListCreationDTO> products = shoppingListCreationDTO.getProductShoppingLists();
            if(products!=null){
                for(ProductShoppingListCreationDTO product: products){
                    product.setShop_list_id(shoppingList.getId());
                    productShoppingListService.save(product);
                }
            }
            return ResponseEntity.ok().build();
        } else {
            throw new RuntimeException("Token not found or invalid");
        }
    }

    @PostMapping("/addProductToShoppingList")
    public ResponseEntity<Void> addProductToShoppingList(){


        return ResponseEntity.ok().build();
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Integer id) {
        // Usuwanie wszystkich powiązanych rekordów z ProductShoppingList na podstawie shoppingListId
        productShoppingListService.deleteAllByShoppingListId(id);

        // Usunięcie samej listy zakupowej
        shoppingListService.deleteShoppingListById(id);

        return ResponseEntity.ok().build();
    }
    @PatchMapping("toggle/{id}/{productshoppinglistId}")
    public ResponseEntity<Void> toggleProductsInShoppingList(@PathVariable Integer id, @PathVariable Integer productshoppinglistId) {
        productShoppingListService.toggleProductDoneStatus(productshoppinglistId);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("like/{id}")
    public ResponseEntity<Void> likeShoppingList(@PathVariable Integer id){
        ShoppingList shoppingList = shoppingListService.findShoppingListById(id);
        shoppingList.setLikedList(!shoppingList.isLikedList());
        shoppingListService.save(shoppingList);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("edit/{id}")
    public ResponseEntity<Void>editShoppingList(@PathVariable Integer id, @RequestBody EditShoppingListDTO request){

        ShoppingList shoppingList = shoppingListService.findShoppingListById(id);

        // Ustawienie nowych wartości dla listy zakupowej
        shoppingList.setName(request.getName());
        shoppingList.setCardColor(request.getCardColor());
        shoppingList.setShopping_date(request.getShopping_date());
        shoppingList.setLikedList(request.isLikedList());

        // Usuwanie wszystkich powiązanych rekordów z ProductShoppingList
        productShoppingListService.deleteAllByShoppingListId(shoppingList.getId());

        // Dodawanie nowych rekordów do ProductShoppingList
        List<ProductShoppingListCreationDTO> products = request.getProductShoppingLists();
        if (products != null) {
            for (ProductShoppingListCreationDTO product : products) {
                product.setShop_list_id(shoppingList.getId());
                productShoppingListService.save(product);
            }
        }
        // Zapisanie zaktualizowanej listy zakupowej
        shoppingListService.save(shoppingList);
        return ResponseEntity.ok().build();
    }




}
