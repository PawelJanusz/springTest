package com.springTest.springTest.service;

import com.springTest.springTest.model.Product;
import com.springTest.springTest.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.doReturn;


@ExtendWith({SpringExtension.class}) // mówimy że testujemy springa
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("Find product with id successfully")
    public void testFindProductById(){
        //given
        Product mockProduct = new Product(1, "Nazwa", "Opis", 1, 1);
        doReturn(mockProduct).when(productRepository).findProductById(1);

        //when
        Product receivedProduct = productService.findById(1);

        //then
        Assertions.assertNotNull(receivedProduct);
    }

    @Test
    @DisplayName("Test find all products")
    public void testFindAllProducts(){
        Product mockProduct = new Product(1, "Name", "Opis", 1, 1);
        Product mockProduct2 = new Product(1, "Name", "Opis2", 1, 1);

        doReturn(Arrays.asList(mockProduct, mockProduct2)).when(productRepository).findAll();

                Iterable<Product> allProducts = productService.findAll();
        Assertions.assertEquals(2, ((Collection<?>) allProducts).size());

    }

}
