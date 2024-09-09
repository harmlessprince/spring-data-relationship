package com.harmlessprince.springdatarelationship.service.implementations;

import com.harmlessprince.springdatarelationship.dtos.requests.ZipCodeRequestDto;
import com.harmlessprince.springdatarelationship.exceptions.NotFoundException;
import com.harmlessprince.springdatarelationship.models.City;
import com.harmlessprince.springdatarelationship.models.ZipCode;
import com.harmlessprince.springdatarelationship.repositories.ZipCodeRepository;
import com.harmlessprince.springdatarelationship.service.interfaces.ZipCodeServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ZipCodeService implements ZipCodeServiceInterface {

    private final ZipCodeRepository zipCodeRepository;
    private final CityService cityService;
    public ZipCodeService(ZipCodeRepository zipCodeRepository, CityService cityService) {
        this.zipCodeRepository = zipCodeRepository;
        this.cityService = cityService;
    }

    @Override
    public List<ZipCode> getZipCodes() {
        return new ArrayList<>(zipCodeRepository.findAll());
    }

    @Transactional
    @Override
    public ZipCode addZipCode(ZipCodeRequestDto zipCodeRequestDto) {

        ZipCode zipCode = new ZipCode();
        zipCode.setName(zipCodeRequestDto.getName());
        if (zipCodeRequestDto.getCityId() == null){
           return zipCodeRepository.save(zipCode);
        }
        City city = cityService.getCity(zipCodeRequestDto.getCityId());
        zipCode.setCity(city);
        return zipCodeRepository.save(zipCode);
    }

    @Override
    public ZipCode addCityToZipCode(Integer zipCodeId, Integer cityId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        City city = cityService.getCity(cityId);
        if (Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("Zipcode already has a city");
        }
        zipCode.setCity(city);
        return zipCodeRepository.save(zipCode);
    }

    @Transactional
    @Override
    public ZipCode removeCityFromZipCode(Integer zipCodeId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        if (!Objects.nonNull(zipCode.getCity())){
            throw new IllegalArgumentException("Zipcode does not have a city");
        }
        zipCode.setCity(null);
        return zipCodeRepository.save(zipCode);
    }

    @Override
    public ZipCode getZipCode(Integer zipCodeId) {
         Optional<ZipCode> optionalZipCode = zipCodeRepository.findById(zipCodeId);
         if (optionalZipCode.isEmpty()){
             throw new NotFoundException("Zipcode", zipCodeId);
         }
        return optionalZipCode.get();
    }

    @Override
    public ZipCode updateZipCode(Integer zipCodeId, ZipCodeRequestDto zipCodeRequestDto) {
        ZipCode zipCode = getZipCode(zipCodeId);
        zipCode.setName(zipCodeRequestDto.getName());
        if (zipCodeRequestDto.getCityId() == null){
            return zipCodeRepository.save(zipCode);
        }
        City city = cityService.getCity(zipCodeRequestDto.getCityId());
        zipCode.setCity(city);
        return zipCodeRepository.save(zipCode);
    }

    @Override
    public void deleteZipCode(Integer zipCodeId) {
        ZipCode zipCode = getZipCode(zipCodeId);
        zipCodeRepository.delete(zipCode);
        return;
    }
}
