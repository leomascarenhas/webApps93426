package ca.vanier.products_api.controller;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {

        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody long id) {

        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting the product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT update existing product
    @PutMapping("update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            return ResponseEntity.ok(productService.updateExistingProduct(id, productDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}