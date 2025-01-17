package ca.vanier.products_api.repository;

import ca.vanier.products_api.entity.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    /**
     * Product Repository Integration Test
     */
    @Autowired
    private ProductRepository productRepository;

    // Provide valid test products
    static Stream<Product> provideValidProducts() {
        return Stream.of(
                new Product("Product A", BigDecimal.valueOf(19.99), "Category A"),
                new Product("Product B", BigDecimal.valueOf(29.99), "Category B"),
                new Product("Product C", BigDecimal.valueOf(39.99), "Category C")
        );
    }

    // Provide invalid test products to test constraints
    static Stream<Product> provideInvalidProducts() {
        return Stream.of(
                new Product("", BigDecimal.valueOf(19.99), "Category A"),  // Invalid: Empty description
                new Product("Product D", BigDecimal.valueOf(-10.00), "Category D"), // Invalid: Negative price
                new Product("Product E", BigDecimal.valueOf(29.99), "")  // Invalid: Empty category
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidProducts")
    void testSaveAndFindById_ValidProducts(Product product) {
        Product savedProduct = productRepository.save(product);

        // Verify that the product is saved and can be retrieved
        assertTrue(productRepository.findById(savedProduct.getId()).isPresent());
        assertEquals(product.getDescription(), savedProduct.getDescription());
    }
}
