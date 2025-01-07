package ca.vanier.products_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.service.ProductService;


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
    public ResponseEntity<?> delete(@RequestBody Product product) {

        try {
            productService.deleteProduct(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting the product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}