package com.harmlessprince.springdatarelationship.repositories;

import com.harmlessprince.springdatarelationship.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}