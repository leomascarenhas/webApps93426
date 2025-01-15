package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    static Stream<Product> provideValidProducts() {
        return Stream.of(
                new Product("Valid Product 1", BigDecimal.valueOf(19.99), "Category A"),
                new Product("Valid Product 2", BigDecimal.valueOf(29.99), "Category B")
        );
    }

    static Stream<Product> provideInvalidProducts() {
        return Stream.of(
                new Product("", BigDecimal.valueOf(19.99), "Category A"),  // Invalid: Empty description
                new Product("Product", BigDecimal.valueOf(-1.00), "Category B"), // Invalid: Negative price
                new Product("Product", BigDecimal.valueOf(29.99), "")  // Invalid: Empty category
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidProducts")
    void testSaveValidProducts(Product product) {
        assertDoesNotThrow(() -> productService.save(product));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidProducts")
    void testSaveInvalidProducts(Product product) {
        assertThrows(IllegalArgumentException.class, () -> productService.save(product));
    }
}
