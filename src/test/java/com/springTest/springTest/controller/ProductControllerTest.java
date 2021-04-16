package com.springTest.springTest.controller;

import com.springTest.springTest.model.Product;
import com.springTest.springTest.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith({SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test product found - GET /product/1")
    public void testGETProductFound() throws Exception {
        // Given
        Product mockProduct = new Product(1, "Product", "Opis", 1, 1);
        doReturn(mockProduct).when(productService).findById(mockProduct.getId());
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}", mockProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("Test product found - GET /product")
    public void testGETALLProductsFound() throws Exception {
        // Given
        Product mockProduct = new Product(1, "Product", "Opis", 1, 1);
        Product mockProduct2 = new Product(2, "Product", "Opis", 1, 1);
        doReturn(Arrays.asList(mockProduct, mockProduct2)).when(productService).findAll();
        mockMvc.perform(MockMvcRequestBuilders.get("/product", mockProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

}
