package ca.vanier.products_api;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.exception.ProductNotFoundException;
import ca.vanier.products_api.repository.ProductRepository;
import ca.vanier.products_api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setDescription("Sample Product");
        sampleProduct.setPrice(BigDecimal.valueOf(99.99));
        sampleProduct.setCategory("Electronics");
        productService.save(sampleProduct); // Save initial sample product for testing
    }

    @Test
    void testSaveProduct() {
        Product newProduct = new Product();
        newProduct.setDescription("New Product");
        newProduct.setPrice(BigDecimal.valueOf(150.00));
        newProduct.setCategory("Home Appliances");

        Product savedProduct = productService.save(newProduct);

        assertNotNull(savedProduct.getId());
        assertEquals("New Product", savedProduct.getDescription());
    }

    @Test
    void testFindById_ProductExists() {
        Product foundProduct = productService.findById(sampleProduct.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        assertEquals(sampleProduct.getId(), foundProduct.getId());
        assertEquals("Sample Product", foundProduct.getDescription());
    }

    @Test
    void testFindById_ProductNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            productService.findById(999L).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        });
    }

    @Test
    void testUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setDescription("Updated Product");
        updatedProduct.setPrice(BigDecimal.valueOf(120.00));
        updatedProduct.setCategory("Updated Category");

        Product result = productService.updateProduct(sampleProduct.getId(), updatedProduct);

        assertEquals("Updated Product", result.getDescription());
        assertEquals(BigDecimal.valueOf(120.00), result.getPrice());
        assertEquals("Updated Category", result.getCategory());
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product updatedProduct = new Product();
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(999L, updatedProduct));
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(sampleProduct.getId());
        assertFalse(productRepository.findById(sampleProduct.getId()).isPresent());
    }

    @Test
    void testDeleteProduct_NotFound() {
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(999L));
    }

    @Test
    void testFindAllProducts() {
        List<Product> products = productService.findAll();
        assertEquals(1, products.size());
        assertEquals("Sample Product", products.get(0).getDescription());
    }

    @Test
    void testFindByCategory() {
        List<Product> products = productService.findByCategory("Electronics");
        assertEquals(1, products.size());
        assertEquals("Sample Product", products.get(0).getDescription());
    }
}
