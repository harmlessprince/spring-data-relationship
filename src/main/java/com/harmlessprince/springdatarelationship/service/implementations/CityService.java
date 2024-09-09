package com.harmlessprince.springdatarelationship.service.implementations;

import com.harmlessprince.springdatarelationship.dtos.requests.CityRequestDto;
import com.harmlessprince.springdatarelationship.exceptions.NotFoundException;
import com.harmlessprince.springdatarelationship.models.City;
import com.harmlessprince.springdatarelationship.repositories.CityRepository;
import com.harmlessprince.springdatarelationship.service.interfaces.CityServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService implements CityServiceInterface {

    private  final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getCities() {
        return new ArrayList<>(cityRepository.findAll());
    }

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public City getCity(Integer cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()){
            throw  new NotFoundException("City", cityId);
        }
        return cityOptional.get();
    }

    @Transactional
    @Override
    public City updateCity(Integer cityId, CityRequestDto cityRequestDto) {
        City cityToEdit = getCity(cityId);
        cityToEdit.setName(cityRequestDto.getName());
        return cityRepository.save(cityToEdit);
    }

    @Override
    public void deleteCity(Integer cityId) {
        City city = getCity(cityId);
        cityRepository.delete(city);
        return;
    }
}
