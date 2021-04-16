package com.springTest.springTest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springTest.springTest.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;


@ExtendWith({SpringExtension.class})
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() throws IOException {
        //given
        File dataJson = Paths.get("src", "test", "resources", "products.json").toFile();
        Product[] products = new ObjectMapper().readValue(dataJson, Product[].class);
        Arrays.stream(products).forEach(productRepository::save);
    }

    @Test
    @DisplayName("Test product not found with non existing ID") //opis testu
    public void testProductNotFoundWithNonExistingId(){
        //when
        Product obtainProduct = productRepository.findProductById(10000);
        //then
        Assertions.assertNull(obtainProduct);
    }

    @Test
    @DisplayName("Test product added successfully")
    public void testProductAddedSuccessfully(){
        //given
        Product expectedProduct = new Product("Name", "Opis", 1);
        Product savedProduct = productRepository.save(expectedProduct);
        //when

        //then
        Assertions.assertNotNull(savedProduct, "Product should be saved");
        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertNotNull(expectedProduct.getName(), savedProduct.getName());
    }


    @Test
    @DisplayName("Test update product")
    public void testUpdateProduct(){
        // Given
        Product productToUpdate = new Product(1, "Updated product", "Opis1", 1, 1);
        // When
        Product updatedProduct = productRepository.save(productToUpdate);
        // Then
        Assertions.assertEquals(productToUpdate.getName(), updatedProduct.getName());
    }


}
