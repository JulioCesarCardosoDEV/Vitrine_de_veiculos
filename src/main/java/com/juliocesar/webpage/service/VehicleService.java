package com.juliocesar.webpage.service;

import com.juliocesar.webpage.dto.VehicleDTO;
import com.juliocesar.webpage.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

   public List<VehicleDTO> findAll(){
        List<VehicleDTO> vehicleList = vehicleRepository.findAll()
                .stream().map(VehicleDTO::new).collect(Collectors.toList());

        return vehicleList;
    }
}
