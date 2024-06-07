package com.juliocesar.webpage.service;

import com.juliocesar.webpage.dto.VehicleDTO;
import com.juliocesar.webpage.entities.Vehicle;
import com.juliocesar.webpage.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

   public List<Vehicle> findAll(){
        List<Vehicle> vehicleList = vehicleRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        return vehicleList;
    }

    public Vehicle cadastraVehicle(Vehicle obj){
        return vehicleRepository.save(obj);
    }

    public Vehicle fromDTO(VehicleDTO objDTO) {
        return new Vehicle(objDTO.getId(), objDTO.getNome(), objDTO.getMarca(), objDTO.getModelo(), objDTO.getPrice(), objDTO.getImage());
    }
}
