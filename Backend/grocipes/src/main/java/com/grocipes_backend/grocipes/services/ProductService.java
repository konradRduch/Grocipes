package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.DTO.GetProductsDTO;
import com.grocipes_backend.grocipes.models.DTO.NutrientDTO;
import com.grocipes_backend.grocipes.models.DTO.ProductDTO;
import com.grocipes_backend.grocipes.models.Product;
import com.grocipes_backend.grocipes.models.UnitRecipeProduct;
import com.grocipes_backend.grocipes.repositories.ProductRepository;
import com.grocipes_backend.grocipes.repositories.UnitRecipeProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UnitRecipeProductRepository unitRecipeProductRepository;

    public ProductService(ProductRepository productRepository,
                          UnitRecipeProductRepository unitRecipeProductRepository) {
        this.productRepository = productRepository;
        this.unitRecipeProductRepository = unitRecipeProductRepository;
    }
    public List<Product>getAllProducts(){
       return productRepository.findAll();
    }

    @Transactional
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
    public Optional<Product>addProduct(ProductDTO dto){
        UnitRecipeProduct unitRecipeProduct= unitRecipeProductRepository.findById(dto.getUnitId())
                .orElseThrow(() -> new NoSuchElementException("No UnitId found with ID: " + dto.getUnitId()));

        Product product = new Product(dto.getName(),dto.getWeight(),dto.getPrice(),dto.getImage_url(),dto.getCalories(),unitRecipeProduct);
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
                            new ArrayList<>(),
                            (Integer) o[6]
                    )
            );

            // Tworzenie NutrientDTO
            NutrientDTO nutrientDTO = new NutrientDTO(
                    (String) o[9],   // Nazwa składnika odżywczego
                    (double) o[7],  // Ilość składnika
                    (double) o[8]  // Wartość dzienna
            );

            // Dodajemy nutrientDTO do listy w GetProductsDTO
            getProductsDTO.getNutrient().add(nutrientDTO);

            // Zapisujemy zaktualizowany obiekt do mapy (jeśli jest nowy)
            productsMap.put(productId, getProductsDTO);
        }

        // Zwracamy listę produktów
        return new ArrayList<>(productsMap.values());
    }

    public Product findById(Integer id) {
        return this.productRepository.findProductById(id);
    }

    public void save(Product product, Integer unitId) {
        UnitRecipeProduct unitRecipeProduct= unitRecipeProductRepository.findById(unitId)
                .orElseThrow(() -> new NoSuchElementException("No UnitId found with ID: " + unitId));
        product.setUnit(unitRecipeProduct);

        this.productRepository.save(product);
    }
}
