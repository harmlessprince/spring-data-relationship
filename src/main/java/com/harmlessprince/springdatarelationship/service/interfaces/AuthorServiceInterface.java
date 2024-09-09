package com.harmlessprince.springdatarelationship.service.interfaces;

import com.harmlessprince.springdatarelationship.dtos.requests.AuthorRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.AuthorResponseDto;
import com.harmlessprince.springdatarelationship.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorServiceInterface {

    public List<AuthorResponseDto> getAuthors();

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    public AuthorResponseDto updateAuthor(Integer authorId, AuthorRequestDto authorRequestDto);

    public AuthorResponseDto getAuthorById(Integer authorId);

    public Author getAuthor(Integer authorId);

    public void deleteAuthorById(Integer authorId);

    public AuthorResponseDto addZipCodeToAuthor(Integer authorId, Integer zipCodeId);

    public AuthorResponseDto deleteZipCodeFromAuthor(Integer authorId);
}

//    public void add();
//    public void get();
//
//    public void delete();
//
//    public void update();