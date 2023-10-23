package com.example.crud.Service;

import com.example.crud.dto.ProductRequestDTO;
import org.springframework.http.ResponseEntity;


public interface ProductService {

    ResponseEntity<Object> getProducts();

    ResponseEntity<Object> createProduct(ProductRequestDTO productRequest);
    ResponseEntity<Object> updateProduct(ProductRequestDTO productRequest);

    ResponseEntity<Object> deleteProduct(Long id);
}
