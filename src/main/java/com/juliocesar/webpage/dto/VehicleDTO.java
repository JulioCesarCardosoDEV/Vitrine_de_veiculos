package com.juliocesar.webpage.dto;

import com.juliocesar.webpage.entities.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO{
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

    public VehicleDTO(){

    }

    public VehicleDTO(Vehicle obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.marca = obj.getMarca();
        this.modelo = obj.getModelo();
        this.price = obj.getPrice();
        this.image = obj.getImage();
    }

}
