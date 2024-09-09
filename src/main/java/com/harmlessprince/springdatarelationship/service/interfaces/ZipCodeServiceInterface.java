package com.harmlessprince.springdatarelationship.service.interfaces;

import com.harmlessprince.springdatarelationship.dtos.requests.ZipCodeRequestDto;
import com.harmlessprince.springdatarelationship.models.ZipCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZipCodeServiceInterface {

    public List<ZipCode> getZipCodes();
    public ZipCode addZipCode(ZipCodeRequestDto zipCodeRequestDto);

    public ZipCode addCityToZipCode(Integer zipCodeId, Integer cityId);
    public ZipCode removeCityFromZipCode(Integer zipCodeId);
    public ZipCode getZipCode(Integer zipCodeId);

    public ZipCode updateZipCode(Integer zipCodeId, ZipCodeRequestDto zipCodeRequestDto);

    public void deleteZipCode(Integer zipCodeId);
}
