package ca.vanier.products_api;
import ca.vanier.products_api.entity.Category;
import ca.vanier.products_api.util.GlobalLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ca.vanier.products_api.repository")
@ComponentScan("ca.vanier.products_api.service")
@ComponentScan("ca.vanier.products_api.controller")
public class ProductsApiApplication {
	public static void main(String[] args) {
		Category category = new Category();
		category.setName("Category");
		GlobalLogger.info(ProductsApiApplication.class, category.getName() + " Hello World");
		SpringApplication.run(ProductsApiApplication.class, args);
	}
}
