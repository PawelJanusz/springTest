package com.springTest.springTest.service;


import com.springTest.springTest.model.Product;
import com.springTest.springTest.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Integer id) {
        return productRepository.findProductById(id);
    }

    public Iterable<Product> findAll() {
     return productRepository.findAll();
    }
}
