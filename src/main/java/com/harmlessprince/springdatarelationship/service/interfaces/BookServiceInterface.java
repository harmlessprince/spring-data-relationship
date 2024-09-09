package com.harmlessprince.springdatarelationship.service.interfaces;

import com.harmlessprince.springdatarelationship.dtos.requests.BookRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.BookResponseDto;
import com.harmlessprince.springdatarelationship.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookServiceInterface {
    public BookResponseDto addBook(BookRequestDto bookRequestDto);

    public Book getBook(Integer bookId);
    public BookResponseDto getBookById(Integer bookId);

    public List<BookResponseDto> getBooks();

    public void deleteBook(Integer bookId);

    public BookResponseDto updateBook(Integer bookId, BookRequestDto bookRequestDto);

    public BookResponseDto addAuthorToBook(Integer bookId, Integer authorId);

    public BookResponseDto deleteAuthorFromBook(Integer bookId, Integer authorId);


    public BookResponseDto addCategoryToBook(Integer bookId, Integer categoryId);

    public BookResponseDto removeCategoryFromBook(Integer bookId, Integer categoryId);


}
