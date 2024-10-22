package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.ProductShoppingListCreationDTO;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.models.ProductShoppingList;
import com.grocipes_backend.grocipes.models.ShoppingList;
import com.grocipes_backend.grocipes.models.UnitProductShoppingList;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import com.grocipes_backend.grocipes.repositories.ProductShoppingListRepository;
import com.grocipes_backend.grocipes.repositories.ShoppingListRepository;
import com.grocipes_backend.grocipes.repositories.UnitProductShoppingListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductShoppingListService {
    private final ProductShoppingListRepository productShoppingListRepository;
    private final UnitProductShoppingListRepository unitProductShoppingListRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;

    public ProductShoppingListService(ProductShoppingListRepository productShoppingListRepository, UnitProductShoppingListRepository unitProductShoppingListRepository, ShoppingListRepository shoppingListRepository, ProductRepository productRepository) {
        this.productShoppingListRepository = productShoppingListRepository;
        this.unitProductShoppingListRepository = unitProductShoppingListRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
    }

    public void save(ProductShoppingListCreationDTO product){

        Product newProduct =  productRepository.findProductById(product.getProduct_id());
        ShoppingList newShoppingList = shoppingListRepository.findShoppingListById(product.getShop_list_id());
        UnitProductShoppingList newUnitProductShoppingList = unitProductShoppingListRepository.findUnitProductShoppingListById(product.getUnit_id());


        ProductShoppingList productShoppingList = new ProductShoppingList();
        productShoppingList.setQuantity(product.getQuantity());
        productShoppingList.setDone(product.isDone());
        productShoppingList.setProduct(newProduct);
        productShoppingList.setShoppingList(newShoppingList);
        productShoppingList.setUnitProductShoppingList(newUnitProductShoppingList);

        productShoppingListRepository.save(productShoppingList);
    }

    public List<ProductShoppingList> findProductShoppingListByShoppingList(ShoppingList shoppingList){
       return productShoppingListRepository.findProductShoppingListByShoppingList(shoppingList);
    }
    @Transactional
    public void deleteProductShoppingListByProductId(Integer productId){
        productShoppingListRepository.deleteByProductId(productId);
    }
    @Transactional
    public void deleteAllByShoppingListId(Integer shoppingListId) {
        productShoppingListRepository.deleteByShoppingListId(shoppingListId);
    }

//    public void toggleProductDoneStatus(Integer productId){
//        ProductShoppingList productShoppingList = productShoppingListRepository.findById(productId)
//                .orElseThrow(NoSuchElementException::new);
//        productShoppingList.setDone(!productShoppingList.isDone());
//        productShoppingListRepository.save(productShoppingList);
//    }
}
