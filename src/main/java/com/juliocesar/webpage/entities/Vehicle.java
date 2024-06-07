package com.juliocesar.webpage.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity(name="veiculos")
@Table(name="veiculos")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "marca")
    private String marca;

    @NotBlank
    @Column(name = "modelo")
    private String modelo;

    @Column(name = "price")
    private double price;

    @NotBlank
    @Column(name = "image")
    private String image;

    public Vehicle(){

    }

    public Vehicle(Long id, String nome, String marca, String modelo, double price, String image) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.price = price;
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicles = (Vehicle) o;
        return Objects.equals(id, vehicles.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
