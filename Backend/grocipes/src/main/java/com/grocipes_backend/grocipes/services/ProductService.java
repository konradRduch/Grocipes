package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.GetProductsDTO;
import com.grocipes_backend.grocipes.models.DTO.NutrientDTO;
import com.grocipes_backend.grocipes.models.DTO.ProductDTO;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product>getAllProducts(){
       return productRepository.findAll();
    }

    public Optional<Product>addProduct(ProductDTO dto){
        Product product = new Product(dto.getName(),dto.getWeight(),dto.getPrice(),dto.getImage_url(),dto.getCalories());
        return Optional.of(productRepository.save(product));
    }

    public Integer getProductIdByName(String name){
        List<Product> products = productRepository.findByName(name);
        if (products.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        return products.get(0).getId();
    }

    public List<GetProductsDTO> getProducts() {
        List<Object[]> objects = productRepository.findproducts();
        Map<Integer, GetProductsDTO> productsMap = new HashMap<>();

        for (Object[] o : objects) {
            Integer productId = (Integer) o[0];

            // Jeśli produkt już istnieje na mapie, użyj go, jeśli nie, stwórz nowy obiekt GetProductsDTO
            GetProductsDTO getProductsDTO = productsMap.getOrDefault(productId,
                    new GetProductsDTO(
                            productId,
                            (String) o[1],
                            (double) o[2],
                            (double) o[3],
                            (String) o[4],
                            (Integer) o[5],
                            new ArrayList<>()
                    )
            );

            // Tworzenie NutrientDTO
            NutrientDTO nutrientDTO = new NutrientDTO(
                    (String) o[8],   // Nazwa składnika odżywczego
                    (double) o[6],  // Ilość składnika
                    (double) o[7]   // Wartość dzienna
            );

            // Dodajemy nutrientDTO do listy w GetProductsDTO
            getProductsDTO.getNutrient().add(nutrientDTO);

            // Zapisujemy zaktualizowany obiekt do mapy (jeśli jest nowy)
            productsMap.put(productId, getProductsDTO);
        }

        // Zwracamy listę produktów
        return new ArrayList<>(productsMap.values());
    }

}
