package com.harmlessprince.springdatarelationship.controllers;

import com.harmlessprince.springdatarelationship.dtos.requests.CityRequestDto;
import com.harmlessprince.springdatarelationship.models.City;
import com.harmlessprince.springdatarelationship.service.implementations.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> index(){
        List<City> cities = cityService.getCities();
        return  ResponseEntity.ok().body(cities);
    }

    @PostMapping
    public ResponseEntity<City> store(@RequestBody CityRequestDto cityRequestDto){
        City city = cityService.addCity(cityRequestDto);
        return  ResponseEntity.ok().body(city);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> show(@PathVariable Integer cityId){
        City city = cityService.getCity(cityId);
        return  ResponseEntity.ok().body(city);
    }

    @PatchMapping("/{cityId}")
    public ResponseEntity<City> update(@PathVariable Integer cityId, @RequestBody CityRequestDto cityRequestDto){
        City city = cityService.updateCity(cityId, cityRequestDto);
        return  ResponseEntity.ok().body(city);
    }

    @DeleteMapping("/{cityId}")
    public void  destroy(@PathVariable Integer cityId){
       cityService.deleteCity(cityId);
//        Map<String, String> = new Map<String, String>()
//        return  ResponseEntity.ok().;
    }
}
