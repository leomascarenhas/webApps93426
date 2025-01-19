package ca.vanier.products_api.service;

import java.util.List;
import java.util.Optional;

import ca.vanier.products_api.entity.Product;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Long id);

    Product updateExistingProduct(Long id, Product productDetails);

    List<Product> findAll();

    void deleteProduct(Long id);

}
