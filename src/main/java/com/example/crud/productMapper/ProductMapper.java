package com.example.crud.productMapper;

import com.example.crud.dto.ProductRequestDTO;
import com.example.crud.dto.ProductResponseDTO;
import com.example.crud.models.ProductEntity;

public class ProductMapper {
    public static ProductEntity toEntity(ProductRequestDTO productRequest) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setDate(productRequest.getDate());
        return productEntity;
    }

    public static ProductResponseDTO toResponseDTO(ProductEntity productEntity) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(productEntity.getId());
        responseDTO.setName(productEntity.getName());
        responseDTO.setPrice(productEntity.getPrice());
        responseDTO.setDate(productEntity.getDate());
        responseDTO.setAntiguedad(productEntity.getAntiguedad());
        return responseDTO;
    }
}

