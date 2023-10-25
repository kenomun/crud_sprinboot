package com.example.crud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class ProductRequestDTO {
    private Long id;

    @NotBlank(message = "El nombre no debe estar vacío")
    @Size(max = 15)
    private String name;

    @NotNull(message = "El campo 'price' no debe estar vacío")
    @DecimalMin(value = "0.0", inclusive = false)
    private Float price;


    @NotNull(message = "El campo 'date' no debe estar vacío")
    @Past
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

