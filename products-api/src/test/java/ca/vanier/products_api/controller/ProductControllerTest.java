package ca.vanier.products_api.controller;

import ca.vanier.products_api.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
                .jsonPath("$.description").isEqualTo("New Product")
                .jsonPath("$.price").isEqualTo(49.99)
                .jsonPath("$.category").isEqualTo("Category A");
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(products, "Products list should not be null");
        assertFalse(products.isEmpty(), "Products list should not be empty");
    }

    @Test
    void testGetProductById() {
        Product product = new Product("Test Product", BigDecimal.valueOf(99.99), "Test Category");

        Product createdProduct = webTestClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(createdProduct);
        Long productId = createdProduct.getId();

        webTestClient.get()
                .uri("/products/{id}", productId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(productId)
                .jsonPath("$.description").isEqualTo("Test Product");
    }
}
