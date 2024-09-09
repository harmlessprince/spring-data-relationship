package com.harmlessprince.springdatarelationship.repositories;

import com.harmlessprince.springdatarelationship.models.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Integer> {
}