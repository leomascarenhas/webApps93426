package ca.vanier.products_api.controller;

import ca.vanier.products_api.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateProduct() {
        Product product = new Product("New Product", BigDecimal.valueOf(49.99), "Category A");

        webTestClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.description").isEqualTo("New Product");
    }

    @Test
    void testGetAllProducts() {
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class);
    }
}
