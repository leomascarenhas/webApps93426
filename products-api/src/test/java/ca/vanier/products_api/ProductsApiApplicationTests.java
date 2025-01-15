package ca.vanier.products_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testHealthEndpoint() {
		ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);

		// Validate that the response status is 200 (OK)
		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Health endpoint should return HTTP 200");
	}

	@Test
	void testInvalidEndpointReturns404() {
		ResponseEntity<String> response = restTemplate.getForEntity("/invalid-endpoint", String.class);

		// Validate that the response status is 404 (NOT FOUND)
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value(), "Invalid endpoint should return HTTP 404");
	}

	@Test
	void testCreateProduct() {
		String newProductJson = """
            {
                "description": "New Test Product",
                "price": 49.99,
                "category": "TestCategory"
            }
        """;

		ResponseEntity<String> response = restTemplate.postForEntity("/product/save", newProductJson, String.class);

		// Validate that the response status is 201 (CREATED)
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value(), "Create product should return HTTP 201");
	}

	@Test
	void testFetchAllProducts() {
		ResponseEntity<String> response = restTemplate.getForEntity("/product", String.class);

		// Validate that the response status is 200 (OK)
		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value(), "Fetch all products should return HTTP 200");
	}
}
