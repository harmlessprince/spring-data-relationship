package com.harmlessprince.springdatarelationship.repositories;

import com.harmlessprince.springdatarelationship.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}