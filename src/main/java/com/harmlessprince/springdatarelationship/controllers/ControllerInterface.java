package com.harmlessprince.springdatarelationship.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public interface ControllerInterface<T, R> {
    public ResponseEntity<List<T>> index();

    public ResponseEntity<T> store(@RequestBody R requestDto);
    public ResponseEntity<T> show(@PathVariable Integer id);

    public ResponseEntity<T> update(@PathVariable Integer id, @RequestBody R requestDto);

    public void destroy(@PathVariable Integer id);
}
