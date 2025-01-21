package ca.vanier.products_api.service;

import java.util.List;
import java.util.Optional;

import ca.vanier.products_api.entity.Product;

public interface ProductService {

    Product save(Product product);

    Product update(Long id, Product productDetails);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void delete(Long id);

}
