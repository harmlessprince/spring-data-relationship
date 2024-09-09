package com.harmlessprince.springdatarelationship.repositories;

import com.harmlessprince.springdatarelationship.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}