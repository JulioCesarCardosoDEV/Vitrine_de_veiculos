package com.juliocesar.webpage.controller;

import com.juliocesar.webpage.dto.UserDTO;
import com.juliocesar.webpage.dto.VehicleDTO;
import com.juliocesar.webpage.entities.Vehicle;
import com.juliocesar.webpage.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<VehicleDTO>> findAll(){
        List<Vehicle> list = vehicleService.findAll();
        List<VehicleDTO> listDTO = list.stream().map(x -> new VehicleDTO(x))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastraVehicle(@RequestBody VehicleDTO objDTO){
        Vehicle obj = vehicleService.fromDTO(objDTO);
        obj = vehicleService.cadastraVehicle(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
