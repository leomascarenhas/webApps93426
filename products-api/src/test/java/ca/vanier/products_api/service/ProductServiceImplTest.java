package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.exception.ProductNotFoundException;
import ca.vanier.products_api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        productRepository.save(new Product("Product A", BigDecimal.valueOf(19.99), "Category A"));
        productRepository.save(new Product("Product B", BigDecimal.valueOf(29.99), "Category B"));
    }

    @Test
    void testFindAll() {
        List<Product> products = productService.findAll();
        assertEquals(2, products.size());
    }

    @Test
    void testFindById() {
        Product product = productRepository.findAll().get(0);
        Optional<Product> foundProduct = productService.findById(product.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Product A", foundProduct.get().getDescription());
    }

    @Test
    void testSave() {
        Product newProduct = new Product("Product C", BigDecimal.valueOf(39.99), "Category C");
        Product savedProduct = productService.save(newProduct);

        assertNotNull(savedProduct.getId());
        assertEquals("Product C", savedProduct.getDescription());
    }

    @Test
    void testUpdateProduct() {
        Product product = productRepository.findAll().get(0);
        Product updatedProduct = new Product("Updated Product A", BigDecimal.valueOf(49.99), "Updated Category");

        Product result = productService.updateProduct(product.getId(), updatedProduct);

        assertEquals("Updated Product A", result.getDescription());
        assertEquals(BigDecimal.valueOf(49.99), result.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Product product = productRepository.findAll().get(0);
        productService.deleteProduct(product.getId());

        assertFalse(productRepository.findById(product.getId()).isPresent());
    }

    @Test
    void testFindByCategory() {
        List<Product> products = productService.findByCategory("Category A");
        assertEquals(1, products.size());
        assertEquals("Product A", products.get(0).getDescription());
    }

    @Test
    void testFindByIdThrowsException() {
        assertThrows(ProductNotFoundException.class, () -> productService.findById(999L));
    }
}
