package ca.vanier.products_api.controller;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.service.ProductService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Product product) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.save(product));
    }

    // PUT update existing product
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return ResponseEntity.ok(productService.update(id, productDetails));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);

        return product.isPresent()
            ? ResponseEntity.ok(product)
            : ResponseEntity.notFound().build();
    }
}