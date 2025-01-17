package ca.vanier.products_api.repository;

import ca.vanier.products_api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query methods
    List<Product> findByCategory(String category);
    Page<Product> findByCategory(String category, Pageable pageable);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price, Sort sort);
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findByDescriptionContaining(String keyword);
    List<Product> findByCategoryOrderByPriceAsc(String category);
    long countByCategory(String category);

    // JPQL query
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price > :price")
    List<Product> findExpensiveProductsInCategory(@Param("category") String category, @Param("price") BigDecimal price);

    // Native SQL query
    @Query(value = "SELECT id, description, price, category FROM product WHERE category = :category AND price > :price", nativeQuery = true)
    List<Product> findExpensiveProductsInCategoryNative(@Param("category") String category, @Param("price") BigDecimal price);

    // Update query
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.price = :price WHERE p.category = :category")
    int updateProductPriceByCategory(@Param("price") BigDecimal price, @Param("category") String category);

    // Optional result for nullable queries
    Optional<Product> findByDescription(String description);
}
