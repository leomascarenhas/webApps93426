package ca.vanier.products_api.exception;

public class ProductNotFoundException extends RuntimeException {

    private final Long productId;

    public ProductNotFoundException(String message) {
        super(message);
        this.productId = null;
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.productId = null;
    }

    public ProductNotFoundException(String message, Long productId) {
        super(message);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
