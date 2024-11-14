package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.ProductShoppingListDTO;
import com.grocipes_backend.grocipes.models.DTO.ShoppingListDTO;
import com.grocipes_backend.grocipes.models.ProductShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.repositories.ShoppingListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public void save(ShoppingList shoppingList){
        shoppingListRepository.save(shoppingList);
    }


    public List<Object[]> getAllByUserId(Integer userId) {
        List<Object[]> xd = shoppingListRepository.findShoppingListsWithProductsByUserId(userId);
        return shoppingListRepository.findShoppingListsWithProductsByUserId(userId);
    }
    public List<ShoppingList> findBySchoppingSchedule(ShoppingSchedule shoppingSchedule){
        return shoppingListRepository.findShoppingListByShoppingList(shoppingSchedule);
    }

    public ShoppingListDTO findShoppingListDTOById(Integer id){
       ShoppingList shoppingList =  shoppingListRepository.findShoppingListById(id);
       ShoppingListDTO shoppingListDTO = new ShoppingListDTO(shoppingList.getId(), shoppingList.getName(), shoppingList.getShopping_date(), shoppingList.getCardColor(),shoppingList.isLikedList(),convertToProductShoppingListDTO(shoppingList.getProductShoppingLists()));
       return shoppingListDTO;
    }

    public ShoppingList findShoppingListById(Integer id){
        return shoppingListRepository.findShoppingListById(id);
    }

    private List<ProductShoppingListDTO> convertToProductShoppingListDTO(List<ProductShoppingList> productShoppingLists){
        List<ProductShoppingListDTO> converted = productShoppingLists.stream()
                .map(productShoppingList -> {
                    ProductShoppingListDTO dto = new ProductShoppingListDTO(
                            productShoppingList.getId(),
                            productShoppingList.getProduct().getId(),
                            productShoppingList.getProduct().getName(),
                            productShoppingList.getProduct().getWeight(),
                            productShoppingList.getProduct().getPrice(),
                            productShoppingList.getProduct().getImage_url(),
                            productShoppingList.getProduct().getCalories(),
                            productShoppingList.getShoppingList().getId(),
                            productShoppingList.getQuantity(),
                            productShoppingList.getUnitProductShoppingList().getId(),
                            productShoppingList.getUnitProductShoppingList().getName(),
                            productShoppingList.isDone()
                    );
                    return dto;
                })
                .collect(Collectors.toList());
        return converted;
    }
    @Transactional
    public void deleteShoppingListById(Integer id) {
        this.shoppingListRepository.deleteById(id);
    }




}
