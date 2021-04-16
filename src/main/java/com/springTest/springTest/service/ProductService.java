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

    public Product saveProduct(Product products) {
        return productRepository.save(products);
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    public  Product update (Product productToUpdate, Integer id){
        Product product = productRepository.findProductById(id);
        if (id != null) {
            productRepository.save(productToUpdate);
        }else
            throw new NullPointerException();
        return product;
    }
}
