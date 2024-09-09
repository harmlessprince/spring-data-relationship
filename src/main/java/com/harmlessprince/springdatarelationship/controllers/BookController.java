package com.harmlessprince.springdatarelationship.controllers;

import com.harmlessprince.springdatarelationship.dtos.requests.BookRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.BookResponseDto;
import com.harmlessprince.springdatarelationship.service.implementations.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("books")
public class BookController implements ControllerInterface<BookResponseDto, BookRequestDto>{

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<BookResponseDto>> index() {
        List<BookResponseDto> bookResponseDtos = bookService.getBooks();
        return ResponseEntity.ok().body(bookResponseDtos);
    }

    @PostMapping
    @Override
    public ResponseEntity<BookResponseDto> store(@RequestBody BookRequestDto requestDto) {
        BookResponseDto bookResponseDto = bookService.addBook(requestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<BookResponseDto> show(@PathVariable Integer id) {
        BookResponseDto bookResponseDto = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookResponseDto);
    }


    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<BookResponseDto> update(@PathVariable Integer id, @RequestBody BookRequestDto requestDto) {
        BookResponseDto bookResponseDto = bookService.updateBook(id, requestDto);
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public void destroy(@PathVariable Integer id) {
        bookService.deleteBook(id);
//        return ResponseEntity.noContent();
    }

    @PatchMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<BookResponseDto> addAuthorToBook(@PathVariable Integer bookId, @PathVariable Integer authorId){
        BookResponseDto bookResponseDto = bookService.addAuthorToBook(bookId, authorId);
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<BookResponseDto> removeAuthorFromBook(@PathVariable Integer bookId, @PathVariable Integer authorId){
        BookResponseDto bookResponseDto = bookService.deleteAuthorFromBook(bookId, authorId);
        return ResponseEntity.ok(bookResponseDto);
    }

    @PatchMapping("/{bookId}/categories/{categoryId}")
    public ResponseEntity<BookResponseDto> addCategoryToBook(@PathVariable Integer bookId, @PathVariable Integer categoryId){
        BookResponseDto bookResponseDto = bookService.addCategoryToBook(bookId, categoryId);
        return ResponseEntity.ok(bookResponseDto);
    }

    @DeleteMapping("/{bookId}/categories/{categoryId}")
    public ResponseEntity<BookResponseDto> removeCategoryFromBook(@PathVariable Integer bookId, @PathVariable Integer categoryId){
        BookResponseDto bookResponseDto = bookService.removeCategoryFromBook(bookId, categoryId);
        return ResponseEntity.ok(bookResponseDto);
    }
}
