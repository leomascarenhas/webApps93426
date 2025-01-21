package ca.vanier.products_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ca.vanier.products_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.vanier.Category;
import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

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
            Optional<Category> existingCategory = categoryRepository.findByName(category.getDescription());
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

        if (product.getDescr().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (product.getCategory() == null) {
            throw new IllegalArgumentException("Category cannot be empty");
        }

        return productRepository.save(product);
    }

    // Update an existing product
    public Product update(Long id, Product product) {
        Product existingProduct = findExistingById(id);
        existingProduct.setDescr(product.getDescr());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    // Use transactional to ensure the task is closed after completion
    public void delete(Long id) {
        logger.info("Deleting product: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(product);
        logger.info("Deleted product with id deleted successfully" + id);
    }

    @Override
    public Optional<Product> findById(Long id) throws NoSuchElementException {
        return productRepository.findById(id);
    }

    public Product findExistingById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product with ID %d not found", id)));
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

}
