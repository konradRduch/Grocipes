package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.ProductShoppingListDTO;
import com.grocipes_backend.grocipes.models.DTO.ShoppingListDTO;
import com.grocipes_backend.grocipes.models.DTO.ShoppingScheduleDTO;
import com.grocipes_backend.grocipes.models.ProductShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingSchedule;
import com.grocipes_backend.grocipes.models.UserEntity;
import com.grocipes_backend.grocipes.repositories.ProductShoppingListRepository;
import com.grocipes_backend.grocipes.repositories.ShoppingListRepository;
import com.grocipes_backend.grocipes.repositories.ShoppingScheduleRepository;
import com.grocipes_backend.grocipes.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingScheduleService {
    private  UserEntityRepository userEntityRepository;
    private final ShoppingScheduleRepository shoppingScheduleRepository;
    private ProductShoppingListRepository productShoppingListRepository;
    private ShoppingListRepository shoppingListRepository;

    public ShoppingScheduleService(ShoppingScheduleRepository shoppingScheduleRepository, UserEntityRepository userEntityRepository) {
        this.shoppingScheduleRepository = shoppingScheduleRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public ShoppingSchedule findShoppingScheduleByUserId(Integer userId){
        UserEntity user = userEntityRepository.findById(userId).orElse(new UserEntity());
        return shoppingScheduleRepository.findShoppingScheduleByUserEntity(user);
    }
    public void save(ShoppingSchedule shoppingSchedule){
        shoppingScheduleRepository.save(shoppingSchedule);
    }

    public List<ShoppingScheduleDTO> findByUserId(Integer userId){
        // Pobieramy listę harmonogramów zakupowych dla użytkownika
        List<ShoppingSchedule> shoppingSchedules = shoppingScheduleRepository.findByUserId(userId);

        // Mapujemy encje na DTO przy użyciu lambd
        List<ShoppingScheduleDTO> shoppingScheduleDTOS = shoppingSchedules.stream()
                .map(shoppingSchedule -> {
                    ShoppingScheduleDTO dto = new ShoppingScheduleDTO(shoppingSchedule.getId(),shoppingSchedule.getName(),convertToDto(shoppingSchedule.getShoppingList()), userId);
                    return dto;
                })
                .collect(Collectors.toList()); // Zbieramy wyniki do listy

        return shoppingScheduleDTOS;
    }

    private List<ShoppingListDTO> convertToDto(List<ShoppingList> shoppingLists){
        List<ShoppingListDTO> converted = shoppingLists.stream()
                .map(shoppingList -> {
                    ShoppingListDTO dto = new ShoppingListDTO(shoppingList.getId(),shoppingList.getName(), shoppingList.getShopping_date(), shoppingList.getCardColor(),convertToProductShoppingListDTO(shoppingList.getProductShoppingLists()));
                    return dto;
                })
                .collect(Collectors.toList());
        return converted;
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
}
