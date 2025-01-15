package ca.vanier.products_api;

import ca.vanier.products_api.entity.Product;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductValidationTest {

    private final Validator validator;

    public ProductValidationTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "'Valid Product', 10.99, 'Electronics', true",   // Valid case
            "'', 10.99, 'Electronics', false",              // Invalid: description empty
            "'Valid Product', -1.00, 'Electronics', false", // Invalid: price negative
            "'Valid Product', 10.99, '', false"             // Invalid: category empty
    })
    void testProductValidation(String description, BigDecimal price, String category, boolean expectedIsValid) {
        try {
            Product product = new Product(description, price, category);
            Set<ConstraintViolation<Product>> violations = validator.validate(product);

            // Log violations for debugging purposes
            if (!violations.isEmpty()) {
                violations.forEach(violation -> System.out.println(
                        "Validation Error: " + violation.getPropertyPath() + " " + violation.getMessage()));
            }

            assertEquals(expectedIsValid, violations.isEmpty());
        } catch (Exception ex) {
            // Handle unexpected exceptions during validation
            fail("An unexpected exception occurred during validation: " + ex.getMessage(), ex);
        }
    }
}
