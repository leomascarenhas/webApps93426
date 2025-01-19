package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImpl implements ProductService {
    //for enhanced logging
private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        logger.info("Saving product: " + product.getId());

        if (product.getDescr().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if(product.getPrice()<0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (product.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty");
        }
      
      return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) throws NoSuchElementException {
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            throw new NoSuchElementException("Product with ID " + id + " not found");
        }
    }
    // Update an existing product
    public Product updateExistingProduct(Long id, Product productDetails) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));// Find the product by ID
        existingProduct.setDescr(productDetails.getDescr());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setCategory(productDetails.getCategory());
        return productRepository.save(existingProduct); // Save the updated product

    }

    @Override
    public List<Product> findAll(){
        List<Product> products = new ArrayList<>() {
        };
        productRepository.findAll().forEach(products::add);

        if (products.isEmpty()) {
            throw new NoSuchElementException("No products found");
        }
        return products;
    }

    @Override
    @Transactional
    // Use transactional to ensure the task is closed after completion
    public void deleteProduct(Long id) {
        logger.info("Deleting product: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(product);
        logger.info("Deleted product with id deleted successfully" + id);
    }

}
