package com.juliocesar.webpage.dto;

import com.juliocesar.webpage.entities.Vehicle;

public record VehicleDTO(Long id, String nome, String marca, String modelo, Double price, String image) {
    public VehicleDTO(Vehicle vehicle){
        this(vehicle.getId(), vehicle.getNome(), vehicle.getMarca(), vehicle.getModelo(), vehicle.getPrice(), vehicle.getImage());
    }
}
