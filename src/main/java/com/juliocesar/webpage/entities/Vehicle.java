package com.juliocesar.webpage.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity(name = "veiculos")
@Table(name="veiculos")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "preco")
    private Double price;

    @Column(name = "imagem")
    private String image;

    public Vehicle(){

    }

    public Vehicle(Long id, String nome, String marca, String modelo, Double price, String image) {
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
