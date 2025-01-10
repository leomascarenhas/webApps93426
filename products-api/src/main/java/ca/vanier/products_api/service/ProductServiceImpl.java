package ca.vanier.products_api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import ca.vanier.products_api.exception.ProductNotFoundException;
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

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    //Is cleaner this way
    public Product save(Product product) {
        validateProduct(product);
        logger.info("Saving product {}", product);
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    //Transactional readonly
    public Optional<Product> findById(Long id) {
        logger.info("Fetching product by id {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        existingProduct.setDescr(updatedProduct.getDescr());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    // Use transactional to ensure the task is closed after completion
    public void deleteProduct(Long id) {
        logger.info("Deleting product: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(product);
        logger.info("Deleted product with id deleted successfully{}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
    return StreamSupport.stream(products.spliterator(), false).toList();
    }



//TODO
//    public List<Product> findByCategory(String category) {
//        return List.of();
//    }

    private void validateProduct(Product product) {
        if(product.getId() != null) {
            throw new IllegalArgumentException("Cannot save a product that already has an ID. Use update instead.");
        }

        if(product.getDescr() == null || product.getDescr().trim().isEmpty()) {
            throw new IllegalArgumentException("Product descr cannot be null or empty");
        }

        if(product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }

        if(product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
    }
}

