package com.example.crud.exceptions;

public class ProductExistsException extends RuntimeException {
    public ProductExistsException(String message) {
        super(message);
    }
}
