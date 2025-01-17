package ca.vanier.products_api.exception;

import java.util.Map;

public class ProductValidationException extends RuntimeException {

    private final Map<String, String> validationErrors;

    public ProductValidationException(String message) {
        super(message);
        this.validationErrors = null;
    }

    public ProductValidationException(String message, Throwable cause) {
        super(message, cause);
        this.validationErrors = null;
    }

    public ProductValidationException(String message, Map<String, String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
