package com.example.crud.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name = "product_entity")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column (name="name" , unique = true)
    private String name;

    @Column(name="price")
    private  Float price;

    @Column(name="date")
    private  LocalDate date;

    @Transient
    private  int antiguedad;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, Float price, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public ProductEntity(String name, Float price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public int getAntiguedad() {
        return Period.between(this.date, LocalDate.now()).getYears();
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
