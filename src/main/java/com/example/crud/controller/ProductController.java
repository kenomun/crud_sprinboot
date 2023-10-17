package com.example.crud.controller;

import com.example.crud.Service.ProductServiceImpl;
import com.example.crud.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/V1/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProduct(@RequestBody Product product){
        return this.productService.newProduct(product);

    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        return this.productService.newProduct(product);

    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> DeleteProduct(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);

    }



}
