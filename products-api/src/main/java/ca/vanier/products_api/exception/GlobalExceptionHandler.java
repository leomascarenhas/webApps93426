package ca.vanier.products_api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static void getError(String exceptionType, String message) {
        MDC.put("exception", exceptionType); // Add the exception type to the logging context
        MDC.put("errorMessage", message);   // Add the error message to the logging context
        logger.error("{}: {}", exceptionType, message); // Log the error
        MDC.clear(); // Clear the MDC to avoid stale context for future logs
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        getError("ProductNotFoundException", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                "Product Not Found",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(formatter)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<ErrorResponse> handleProductValidationException(ProductValidationException ex) {
        getError("ProductValidationException", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                "Product Validation Failed",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(formatter)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        getError("MethodArgumentNotValidException", String.join("; ", errors));

        ErrorResponse response = new ErrorResponse(
                "Validation Error",
                String.join("; ", errors),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(formatter)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        getError("RuntimeException", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                "Runtime Exception",
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().format(formatter)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        getError("Exception", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                "Unexpected Error",
                "An unexpected error occurred. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now().format(formatter)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
