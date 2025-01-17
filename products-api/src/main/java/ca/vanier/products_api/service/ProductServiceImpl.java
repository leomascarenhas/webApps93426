package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.exception.ProductNotFoundException;
import ca.vanier.products_api.repository.ProductRepository;
import ca.vanier.products_api.util.GlobalLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        ProductValidator.validate(product);
        GlobalLogger.info(ProductServiceImpl.class, "Saving product: " + product.getDescription());
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        GlobalLogger.info(ProductServiceImpl.class, "Fetching product with ID: " + id);
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        GlobalLogger.info(ProductServiceImpl.class, "Updating product with ID: " + id);
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());

        GlobalLogger.info(ProductServiceImpl.class, "Product with ID: " + id + " updated successfully");
        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        GlobalLogger.info(ProductServiceImpl.class, "Deleting product with ID: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(product);
        GlobalLogger.info(ProductServiceImpl.class, "Product with ID: " + id + " deleted successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        GlobalLogger.info(ProductServiceImpl.class, "Fetching all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category) {
        GlobalLogger.info(ProductServiceImpl.class, "Fetching products in category: " + category);
        return productRepository.findByCategory(category);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Product> findByCategory(String category, Pageable pageable) {
        return null;
    }

    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        GlobalLogger.info(ProductServiceImpl.class, "Fetching products with price between " + minPrice + " and " + maxPrice);
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public long countProductsByCategory(String category) {
        GlobalLogger.info(ProductServiceImpl.class, "Counting products in category: " + category);
        return productRepository.countByCategory(category);
    }
}
