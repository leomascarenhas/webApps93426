package ca.vanier.products_api.service;

import java.util.Optional;

import ca.vanier.product_category.entity.*;
import ca.vanier.products_api.repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product save(Product product) {

        Category category = product.getCategory();
        if (category != null) {
            Optional<Category> existingCategory = categoryRepository.findByName(category.getName());
            if (existingCategory.isPresent()) {
                product.setCategory(existingCategory.get());
            } else {
                categoryRepository.save(category);
            }
        }
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product cannot be null or empty");
        }
        logger.info("Saving product: " + product.getId());
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    //Transactional readonly
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
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