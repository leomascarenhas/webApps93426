package ca.vanier.products_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
    
    
}
