package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.exception.ProductValidationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceValidationTest {

    @Test
    void testValidProduct() {
        Product product = new Product("Valid Product", BigDecimal.valueOf(99.99), "Valid Category");

        assertDoesNotThrow(() -> ProductValidator.validate(product));
    }

    @Test
    void testInvalidDescription() {
        Product product = new Product("", BigDecimal.valueOf(99.99), "Valid Category");

        assertThrows(ProductValidationException.class, () -> ProductValidator.validate(product));
    }

    @Test
    void testInvalidPrice() {
        Product product = new Product("Valid Product", BigDecimal.valueOf(-1), "Valid Category");

        assertThrows(ProductValidationException.class, () -> ProductValidator.validate(product));
    }

    @Test
    void testInvalidCategory() {
        Product product = new Product("Valid Product", BigDecimal.valueOf(99.99), "");

        assertThrows(ProductValidationException.class, () -> ProductValidator.validate(product));
    }
}
