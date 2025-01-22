package ca.vanier.products_api.service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.exception.ProductValidationException;

import java.math.BigDecimal;

public class ProductValidator {

    public static void validate(Product product) {
        if (product.getDescription() == null || product.getDescription().isBlank()) {
            throw new ProductValidationException("Description cannot be null or blank");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductValidationException("Price must be greater than zero");
        }
        if (product.getCategory() == null || product.getCategory().isBlank()) {
            throw new ProductValidationException("Category cannot be null or blank");
        }
    }
}
