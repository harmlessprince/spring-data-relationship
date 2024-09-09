package com.harmlessprince.springdatarelationship.repositories;

import com.harmlessprince.springdatarelationship.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}