package ca.vanier.products_api.service;

import java.util.List;
import java.util.Optional;

import ca.vanier.products_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteProduct(Long id);

    List<Product> findAll();

    Product updateProduct(Long id, Product product);

    //TODO List<Product> findByCategory(String category);
}
