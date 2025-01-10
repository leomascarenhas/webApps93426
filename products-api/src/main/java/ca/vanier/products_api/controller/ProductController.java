package ca.vanier.products_api.controller;

import ca.vanier.products_api.exception.ProductNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.service.ProductService;

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
        Product createProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
    }

    //retrieve all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    //Retrieve by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: "+ id));
        return ResponseEntity.ok(product);
    }

    //Update an existing Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @Valid @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(product);
    }
    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}