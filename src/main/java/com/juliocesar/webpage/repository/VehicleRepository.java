package com.juliocesar.webpage.repository;

import com.juliocesar.webpage.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository <Vehicle, Long> {
}
