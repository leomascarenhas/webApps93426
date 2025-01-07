package ca.vanier.products_api.service;

import java.lang.classfile.ClassFile.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.products_api.entity.Product;
import ca.vanier.products_api.repository.ProductRepository;

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

    @Override
    public void deleteProduct(Long id) throws NoSuchElementException {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

}
