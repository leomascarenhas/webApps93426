package ca.vanier.products_api.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Update an existing product
    public Product updateExistingProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));// Find the product by ID
        if (existingProduct != null) {
            if (existingProduct.fieldCheckOK(productDetails)) {
                existingProduct.setDescr(productDetails.getDescr()); 
                existingProduct.setPrice(productDetails.getPrice());
                existingProduct.setCategory(productDetails.getCategory());
                // Update other fields as needed
                return productRepository.save(existingProduct); // Save the updated product
            }
            else return null;
        }
        return null;  // Return null if the product wasn't found
    }

    @Override
    public void deleteProduct(Long id) throws NoSuchElementException {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

}
