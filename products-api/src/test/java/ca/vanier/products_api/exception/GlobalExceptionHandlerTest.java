//package ca.vanier.products_api.exception;
//
//import ca.vanier.products_api.entity.Product;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import java.math.BigDecimal;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class GlobalExceptionHandlerTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    void testProductNotFoundException() {
//        webTestClient.get()
//                .uri("/products/999")
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectBody()
//                .jsonPath("$.error").isEqualTo("Product Not Found")
//                .jsonPath("$.message").isEqualTo("Product with ID 999 not found");
//    }
//
//    @Test
//    void testValidationException() {
//        Product invalidProduct = new Product("", BigDecimal.valueOf(-1), "");
//
//        webTestClient.post()
//                .uri("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(invalidProduct)
//                .exchange()
//                .expectStatus().isBadRequest()
//                .expectBody()
//                .jsonPath("$.error").isEqualTo("Validation Error");
//    }
//}
