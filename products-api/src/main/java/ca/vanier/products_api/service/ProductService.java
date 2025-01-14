package ca.vanier.products_api.service;

import java.util.Optional;

import ca.vanier.products_api.entity.Product;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteProduct(Long id);

}
