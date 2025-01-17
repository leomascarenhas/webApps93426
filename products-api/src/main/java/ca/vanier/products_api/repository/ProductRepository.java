package ca.vanier.products_api.repository;

import ca.vanier.products_api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

    // Optional result for nullable queries
    Optional<Product> findByDescription(String description);
}
