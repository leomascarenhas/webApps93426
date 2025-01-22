package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    /**
     * Provides a stream of valid Product objects for testing.
     */
    static Stream<Product> provideValidProducts() {
        return Stream.of(
                new Product("Valid Product 1", BigDecimal.valueOf(19.99), "Category A"),
                new Product("Valid Product 2", BigDecimal.valueOf(29.99), "Category B"),
                new Product("Valid Product 3", BigDecimal.valueOf(39.99), "Category C")
        );
    }

    /**
     * Provides a stream of invalid Product objects for testing.
     */
    static Stream<Product> provideInvalidProducts() {
        return Stream.of(
                new Product("", BigDecimal.valueOf(19.99), "Category A"),  // Invalid: Empty description
                new Product("Product", BigDecimal.valueOf(-1.00), "Category B"), // Invalid: Negative price
                new Product("Product", BigDecimal.valueOf(29.99), "")  // Invalid: Empty category
        );
    }

    /**
     * Test saving valid products using parameterized test.
     */
    @ParameterizedTest
    @MethodSource("provideValidProducts")
    void testSaveValidProducts(Product product) {
        assertDoesNotThrow(() -> {
            Product savedProduct = productService.save(product);
            assertNotNull(savedProduct.getId(), "Product ID should not be null after saving");
            assertEquals(product.getDescription(), savedProduct.getDescription());
            assertEquals(product.getPrice(), savedProduct.getPrice());
            assertEquals(product.getCategory(), savedProduct.getCategory());
        });
    }

    /**
     * Test saving invalid products using parameterized test.
     */
    @ParameterizedTest
    @MethodSource("provideInvalidProducts")
    void testSaveInvalidProducts(Product product) {
        assertThrows(jakarta.validation.ConstraintViolationException.class, () -> productService.save(product));
    }
}
