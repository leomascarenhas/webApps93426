package ca.vanier.products_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.products_api.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
