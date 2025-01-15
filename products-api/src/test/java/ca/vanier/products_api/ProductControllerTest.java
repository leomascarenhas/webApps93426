package ca.vanier.products_api;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testCreateAndFetchProduct() {
        String baseUrl = "http://localhost:" + port + "/product";

        // Create a new product
        Product newProduct = new Product("Test Product", BigDecimal.valueOf(19.99), "TestCategory");
        ResponseEntity<Product> response = restTemplate.postForEntity(baseUrl + "/save", newProduct, Product.class);

        // Assert product creation
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Product createdProduct = response.getBody();
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        assertEquals("Test Product", createdProduct.getDescription());

        // Fetch the product
        ResponseEntity<Product> fetchedResponse = restTemplate.getForEntity(baseUrl + "/" + createdProduct.getId(), Product.class);

        // Assert product fetch
        assertEquals(HttpStatus.OK, fetchedResponse.getStatusCode());
        Product fetchedProduct = fetchedResponse.getBody();
        assertNotNull(fetchedProduct);
        assertEquals(createdProduct.getId(), fetchedProduct.getId());
        assertEquals("Test Product", fetchedProduct.getDescription());
    }

    @Test
    void testFetchAllProducts() {
        String baseUrl = "http://localhost:" + port + "/product";

        // Fetch all products
        ResponseEntity<Product[]> response = restTemplate.getForEntity(baseUrl, Product[].class);

        // Assert fetch result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Product[] products = response.getBody();
        assertNotNull(products);
        // Assuming some products are already in the database
    }

    @Test
    void testDeleteProduct() {
        String baseUrl = "http://localhost:" + port + "/product";

        // Create a new product
        Product newProduct = new Product("Test Product", BigDecimal.valueOf(19.99), "TestCategory");
        Product createdProduct = productRepository.save(newProduct);

        // Delete the product
        restTemplate.delete(baseUrl + "/" + createdProduct.getId());

        // Verify product deletion
        assertEquals(false, productRepository.findById(createdProduct.getId()).isPresent());
    }
}
