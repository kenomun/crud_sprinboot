package com.example.crud.Service;

import com.example.crud.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();

    ResponseEntity<Object> createProduct(Product product);
    ResponseEntity<Object> updateProduct(Product product);

    ResponseEntity<Object> deleteProduct(Long id);
}
