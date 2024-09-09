package com.harmlessprince.springdatarelationship.service.implementations;

import com.harmlessprince.springdatarelationship.dtos.requests.AuthorRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.AuthorResponseDto;
import com.harmlessprince.springdatarelationship.exceptions.NotFoundException;
import com.harmlessprince.springdatarelationship.models.Author;
import com.harmlessprince.springdatarelationship.models.ZipCode;
import com.harmlessprince.springdatarelationship.repositories.AuthorRepository;
import com.harmlessprince.springdatarelationship.service.interfaces.AuthorServiceInterface;
import com.harmlessprince.springdatarelationship.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService implements AuthorServiceInterface {

    private final ZipCodeService zipCodeService;

    private final AuthorRepository authorRepository;

    public AuthorService(ZipCodeService zipCodeService, AuthorRepository authorRepository) {
        this.zipCodeService = zipCodeService;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = authorRepository.findAll();
//        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
//        authors.forEach(author -> authorResponseDtos.add(MapperUtil.authorToAuthorResponseDto(author)));
        return MapperUtil.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipCodeId() == null){
            throw  new IllegalArgumentException("Author needs zipcode");
        }
        ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
        author.setZipCode(zipCode);
        Author createdAuthor  = authorRepository.save(author);
        return MapperUtil.authorToAuthorResponseDto(createdAuthor);
    }


    @Transactional
    @Override
    public AuthorResponseDto updateAuthor(Integer authorId, AuthorRequestDto authorRequestDto) {
        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipCodeId() != null){
           ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
           authorToEdit.setZipCode(zipCode);
        }
        Author author = authorRepository.save(authorToEdit);

        return MapperUtil.authorToAuthorResponseDto(author);
    }

    @Override
    public AuthorResponseDto getAuthorById(Integer authorId) {
        Author author = getAuthor(authorId);
        return MapperUtil.authorToAuthorResponseDto(author);
    }

    @Override
    public Author getAuthor(Integer authorId) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isEmpty()){
            throw  new NotFoundException("Author", authorId);
        }
        return optionalAuthor.get();
    }

    @Override
    public void deleteAuthorById(Integer authorId) {
        Author author = getAuthor(authorId);
        authorRepository.delete(author);
        return;
    }


    @Transactional
    @Override
    public AuthorResponseDto addZipCodeToAuthor(Integer authorId, Integer zipCodeId) {
        Author author = getAuthor(authorId);
        ZipCode zipCode = zipCodeService.getZipCode(zipCodeId);
        if (Objects.nonNull(author.getZipCode())){
            throw  new IllegalArgumentException("Author already has zipcode");
        }
        author.setZipCode(zipCode);
        Author updatedAuthor = authorRepository.save(author);
        return MapperUtil.authorToAuthorResponseDto(updatedAuthor);
    }


    @Transactional
    @Override
    public AuthorResponseDto deleteZipCodeFromAuthor(Integer authorId) {
        Author author = getAuthor(authorId);
        if (!Objects.nonNull(author.getZipCode())){
            throw  new IllegalArgumentException("Author does not have zipcode");
        }
        author.setZipCode(null);
        Author updatedAuthor = authorRepository.save(author);
        return MapperUtil.authorToAuthorResponseDto(updatedAuthor);
    }
}
