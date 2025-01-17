package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteProduct(Long id);

    List<Product> findAll();

    Product updateProduct(Long id, Product product);

    List<Product> findByCategory(String category);

    // Add pagination for all products
    Page<Product> findAll(Pageable pageable);

    // Add pagination for products in a specific category
    Page<Product> findByCategory(String category, Pageable pageable);

    // Find products within a price range
    List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    // Count products in a category
    long countProductsByCategory(String category);
}
