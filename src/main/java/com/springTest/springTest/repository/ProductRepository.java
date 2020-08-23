package com.springTest.springTest.repository;

import com.springTest.springTest.model.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {


    Product findProductById(Integer id);
}
