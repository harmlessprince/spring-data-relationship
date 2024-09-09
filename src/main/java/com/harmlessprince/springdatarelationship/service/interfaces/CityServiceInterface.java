package com.harmlessprince.springdatarelationship.service.interfaces;

import com.harmlessprince.springdatarelationship.dtos.requests.CityRequestDto;
import com.harmlessprince.springdatarelationship.models.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityServiceInterface {

    public List<City> getCities();
    public City addCity(CityRequestDto cityRequestDto);
    public City getCity(Integer cityId);

    public City updateCity(Integer cityId, CityRequestDto cityRequestDto);

    public void deleteCity(Integer cityId);

}
