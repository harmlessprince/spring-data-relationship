package com.harmlessprince.springdatarelationship.controllers;

import com.harmlessprince.springdatarelationship.dtos.requests.ZipCodeRequestDto;
import com.harmlessprince.springdatarelationship.dtos.requests.ZipcodeCityRequestDto;
import com.harmlessprince.springdatarelationship.models.ZipCode;
import com.harmlessprince.springdatarelationship.service.implementations.ZipCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("zipcodes/{zipCodeId}/cities")
public class ZipcodeCityController{

    private  final ZipCodeService zipCodeService;

    public ZipcodeCityController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @PatchMapping("/{cityId}")
    public ResponseEntity<ZipCode> addCityToZipCode(@PathVariable Integer zipCodeId,  @PathVariable Integer cityId) {
        ZipCode zipCode = zipCodeService.addCityToZipCode(zipCodeId, cityId);
        return ResponseEntity.ok().body(zipCode);
    }

    @PatchMapping
    public ResponseEntity<ZipCode> removeCityFromZipCode(@PathVariable Integer zipCodeId) {
        ZipCode zipCode = zipCodeService.removeCityFromZipCode(zipCodeId);
        return ResponseEntity.ok().body(zipCode);
    }


}
