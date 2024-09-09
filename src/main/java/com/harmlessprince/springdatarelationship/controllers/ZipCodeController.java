package com.harmlessprince.springdatarelationship.controllers;

import com.harmlessprince.springdatarelationship.dtos.requests.ZipCodeRequestDto;
import com.harmlessprince.springdatarelationship.models.ZipCode;
import com.harmlessprince.springdatarelationship.service.implementations.ZipCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("zipcodes")
public class ZipCodeController implements ControllerInterface<ZipCode, ZipCodeRequestDto>{

    private final ZipCodeService zipCodeService;

    public ZipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @RequestMapping
    @Override
    public ResponseEntity<List<ZipCode>> index() {
        List<ZipCode> zipCodes = zipCodeService.getZipCodes();
        return ResponseEntity.ok().body(zipCodes);
    }

    @PostMapping
    @Override
    public ResponseEntity<ZipCode> store(@RequestBody ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCode = zipCodeService.addZipCode(zipCodeRequestDto);
        return ResponseEntity.ok().body(zipCode);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ZipCode> show(@PathVariable  Integer id) {
        return ResponseEntity.ok().body(zipCodeService.getZipCode(id));
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<ZipCode> update(@PathVariable Integer id, @RequestBody ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCode = zipCodeService.updateZipCode(id, zipCodeRequestDto);
        return  ResponseEntity.ok().body(zipCode);
    }

    @Override
    public void destroy(Integer id) {
        zipCodeService.deleteZipCode(id);
    }
}
