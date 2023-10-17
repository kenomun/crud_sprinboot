package com.example.crud.controller;

import com.example.crud.Service.ProductServiceImpl;
import com.example.crud.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Obtener todos los productos")
    @GetMapping
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @ApiOperation(value = "Agregar un nueveo producto")
    @PostMapping
    public ResponseEntity<Object> registrarProduct(@RequestBody Product product){
        return this.productService.newProduct(product);

    }

    @ApiOperation(value = "Editar un producto")
    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        return this.productService.newProduct(product);

    }

    @ApiOperation(value = "Eliminar un producto")
    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> DeleteProduct(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);

    }



}
