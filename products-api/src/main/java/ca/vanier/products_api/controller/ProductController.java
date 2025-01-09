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

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        Product productCreated = productService.save(product);
        return productCreated;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody long id) {

        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
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