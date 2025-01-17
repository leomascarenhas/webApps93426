package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, JdbcTemplate jdbcTemplate) {
        this.productRepository = productRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setUpdatedAt(product.getUpdatedAt());

        return productRepository.save(existingProduct);
    }

    @Override
    public List<Product> findByCategory(String category) {
        try {
            // JPA Query
            return productRepository.findByCategory(category);
        } catch (Exception e) {
            // Fallback to JDBC Query
            String sql = "SELECT * FROM Product WHERE category = ?";
            return jdbcTemplate.query(
                    sql,
                    new Object[]{category},
                    (rs, rowNum) -> new Product(
                            rs.getLong("id"),
                            rs.getString("description"),
                            rs.getBigDecimal("price"),
                            rs.getString("category"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("updated_at").toLocalDateTime()
                    )
            );
        }
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByCategory(String category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }

    @Override
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        try {
            // JPA Query
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        } catch (Exception e) {
            // Fallback to JDBC Query
            String sql = "SELECT * FROM Product WHERE price BETWEEN ? AND ?";
            return jdbcTemplate.query(
                    sql,
                    new Object[]{minPrice, maxPrice},
                    (rs, rowNum) -> new Product(
                            rs.getLong("id"),
                            rs.getString("description"),
                            rs.getBigDecimal("price"),
                            rs.getString("category"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("updated_at").toLocalDateTime()
                    )
            );
        }
    }

    @Override
    public long countProductsByCategory(String category) {
        try {
            // JPA Query
            return productRepository.countByCategory(category);
        } catch (Exception e) {
            // Fallback to JDBC Query with Null Handling
            String sql = "SELECT COUNT(*) FROM Product WHERE category = ?";
            try {
                Long count = jdbcTemplate.queryForObject(sql, new Object[]{category}, Long.class);
                return count != null ? count : 0L; // Handle null safely
            } catch (EmptyResultDataAccessException ex) {
                return 0L; // No records found
            }
        }
    }
}
