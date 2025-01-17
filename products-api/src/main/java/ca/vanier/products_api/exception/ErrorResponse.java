package ca.vanier.products_api.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(String error, String message, int status, String timestamp) {
    public ErrorResponse(String error, String message, HttpStatus status) {
        this(error, message, status.value(), LocalDateTime.now().toString());
    }
}
