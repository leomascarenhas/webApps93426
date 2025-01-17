package ca.vanier.products_api.controller;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.exception.ProductNotFoundException;
import ca.vanier.products_api.service.ProductService;
import ca.vanier.products_api.util.GlobalLogger;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        GlobalLogger.info(ProductController.class, "Creating a new product: " + product.getDescription());
        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Retrieve all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        GlobalLogger.info(ProductController.class, "Fetching all products");
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    // Retrieve product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        GlobalLogger.info(ProductController.class, "Fetching product with id: " + id);
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return ResponseEntity.ok(product);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @Valid @RequestBody Product updatedProduct) {
        GlobalLogger.info(ProductController.class, "Updating product with id: " + id);
        Product product = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(product);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        GlobalLogger.info(ProductController.class, "Deleting product with id: " + id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
