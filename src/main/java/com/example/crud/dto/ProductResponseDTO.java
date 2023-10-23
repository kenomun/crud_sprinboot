package com.example.crud.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Float price;
    private LocalDate date;
    private int antiguedad;

    public ProductResponseDTO() {
        // Constructor vacío
    }

    public ProductResponseDTO(Long id, String name, Float price, LocalDate date, int antiguedad) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.antiguedad = antiguedad;
    }
}
