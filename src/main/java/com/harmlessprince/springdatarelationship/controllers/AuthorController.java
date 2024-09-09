package com.harmlessprince.springdatarelationship.controllers;

import com.harmlessprince.springdatarelationship.dtos.requests.AuthorRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.AuthorResponseDto;
import com.harmlessprince.springdatarelationship.service.implementations.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("authors")
public class AuthorController implements ControllerInterface<AuthorResponseDto, AuthorRequestDto>{
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<AuthorResponseDto>> index() {
        List <AuthorResponseDto> authorResponseDtos = authorService.getAuthors();
        return ResponseEntity.ok().body(authorResponseDtos);
    }

    @Override
    public ResponseEntity<AuthorResponseDto> store(AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return ResponseEntity.ok().body(authorResponseDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<AuthorResponseDto> show(@PathVariable Integer id) {
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);
        return ResponseEntity.ok().body(authorResponseDto);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<AuthorResponseDto> update(@PathVariable Integer id, AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorResponseDto = authorService.updateAuthor(id, authorRequestDto);
        return ResponseEntity.ok().body(authorResponseDto);
    }

    @DeleteMapping("/{id}")
    @Override
    public void destroy(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);
    }

    @PatchMapping("/{authorId}/zipcodes/{zipCodeId}")
    public ResponseEntity<AuthorResponseDto> addZipcodeToAuthor(@PathVariable Integer authorId, @PathVariable Integer zipCodeId) {
        AuthorResponseDto authorResponseDto = authorService.addZipCodeToAuthor(authorId, zipCodeId);
        return ResponseEntity.ok().body(authorResponseDto);
    }

    @PatchMapping("/{authorId}/zipcodes")
    public ResponseEntity<AuthorResponseDto> removeZipcodeFromAuthor(@PathVariable Integer authorId) {
        AuthorResponseDto authorResponseDto = authorService.deleteZipCodeFromAuthor(authorId);
        return ResponseEntity.ok().body(authorResponseDto);
    }
}
