package com.juliocesar.webpage.Config;

import com.juliocesar.webpage.entities.Vehicle;
import com.juliocesar.webpage.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner{
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {
        Vehicle hb20 = new Vehicle(null, "Hyundai HB20", "Hyundai", "HB20", 52990, "https://image.webmotors.com.br/_fotos/anunciousados/gigante/2024/202406/20240606/hyundai-hb20-1.6-premium-16v-flex-4p-automatico-wmimagem17212356530.jpg?s=fill&w=249&h=186&q=70");
        Vehicle creta = new Vehicle(null, "Hyundai Creta", "Hyundai", "Creta", 89900, "https://image.webmotors.com.br/_fotos/anunciousados/gigante/2024/202405/20240524/hyundai-creta-2-0-16v-flex-prestige-automatico-wmimagem14221366040.webp?s=fill&w=249&h=186&q=70");
        Vehicle bmw320i = new Vehicle(null, "BMW 320i", "BMW", "320i", 359000 , "https://image.webmotors.com.br/_fotos/anunciousados/gigante/2024/202405/20240528/bmw-320i-2-0-16v-turbo-flex-m-sport-automatico-wmimagem15095802325.webp?s=fill&w=249&h=186&q=70");
        vehicleRepository.saveAll(Arrays.asList(hb20, creta, bmw320i));
    }
}
