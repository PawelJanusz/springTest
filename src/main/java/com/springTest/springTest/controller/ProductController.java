package com.springTest.springTest.controller;


import com.springTest.springTest.model.Product;
import com.springTest.springTest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok().body(productService);
    }

    @PostMapping("/addProduct")
    public Product saveProducts(@ModelAttribute Product newProduct){
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setQuantity(newProduct.getQuantity());
        product.setVersion(newProduct.getVersion());
        productService.saveProduct(product);
        return product;
    }
    @PutMapping("{id}")
    public ResponseEntity<?> productUpdate(@PathVariable("id") Integer id, @RequestBody Product product){
        Product product1 = productService.update(product, id);
        if (product1 != null) {
            return ResponseEntity.ok().body(product1);
        }
        return null;
    }

    @DeleteMapping("/deleteProduct")
    public Product productDelete(Integer id){
        Product product = productService.findById(id);
        productService.deleteProduct(id);
        return product;
    }


}
