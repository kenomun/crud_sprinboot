package com.example.crud.dto;

import com.example.crud.models.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductRequestDTO {
    private Long id;
    private String name;
    private Float price;
    private LocalDate date;

    public ProductRequestDTO() {
        // Constructor vacío
    }

    public ProductRequestDTO(Long id, String name, Float price, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

}

