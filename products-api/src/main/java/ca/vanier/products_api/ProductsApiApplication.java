package ca.vanier.products_api;

import ca.vanier.products_api.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiApplication.class, args);
	}
	Product product = new Product("Laptop", new BigDecimal("1299.99"), "Electronics");
}
