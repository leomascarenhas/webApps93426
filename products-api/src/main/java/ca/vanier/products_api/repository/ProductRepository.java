package ca.vanier.products_api.repository;

import ca.vanier.products_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Example custom query method to find products by category
    List<Product> findByCategory(String category);

}
