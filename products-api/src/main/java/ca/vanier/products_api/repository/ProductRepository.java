package ca.vanier.products_api.repository;

import ca.vanier.products_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query methods using BigDecimal

    // Find all products in a specific category
    List<Product> findByCategory(String category);

    // Find products with a price greater than a specific value
    List<Product> findByPriceGreaterThan(BigDecimal price);

    // Find products with a price between a range
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Find products whose description contains a specific keyword
    List<Product> findByDescriptionContaining(String keyword);

    // Find all products in a specific category and sort by price
    List<Product> findByCategoryOrderByPriceAsc(String category);

    // JPQL query using BigDecimal
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price > :price")
    List<Product> findExpensiveProductsInCategory(@Param("category") String category, @Param("price") BigDecimal price);

    // Native SQL query using BigDecimal
    @Query(value = "SELECT * FROM product WHERE category = :category AND price > :price", nativeQuery = true)
    List<Product> findExpensiveProductsInCategoryNative(@Param("category") String category, @Param("price") BigDecimal price);

    // Optional result for nullable queries
    Optional<Product> findByDescription(String description);
}
