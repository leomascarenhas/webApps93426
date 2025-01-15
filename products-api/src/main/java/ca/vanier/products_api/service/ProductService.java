package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteProduct(Long id);

    List<Product> findAll();

    Product updateProduct(Long id, Product product);

    List<Product> findByCategory(String category);
}
