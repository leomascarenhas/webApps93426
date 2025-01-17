package ca.vanier.products_api.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String error, String message, int status) {

    public ErrorResponse(String error, String message, HttpStatus status) {
        this(error, message, status.value());
    }
}
