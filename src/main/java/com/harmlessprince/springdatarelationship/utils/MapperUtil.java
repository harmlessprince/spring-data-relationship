package com.harmlessprince.springdatarelationship.utils;

import com.harmlessprince.springdatarelationship.dtos.responseDtos.AuthorResponseDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.BookResponseDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.CategoryResponseDto;
import com.harmlessprince.springdatarelationship.models.Author;
import com.harmlessprince.springdatarelationship.models.Book;
import com.harmlessprince.springdatarelationship.models.Category;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    public static BookResponseDto bookToBookResponseDto(Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setCategory(book.getCategory().getName());
        List<String> names = new ArrayList<>();
        List<Author> authors = book.getAuthors();
         authors.forEach(author -> names.add(author.getName()));
        bookResponseDto.setAuthorNames(names);
        return  bookResponseDto;
    }

    public static List<BookResponseDto> booksToBookResponseDtos(List<Book> books){
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        books.forEach(book -> bookResponseDtos.add(bookToBookResponseDto(book)));
        return  bookResponseDtos;
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author){
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());
        List<Book> books = author.getBooks();
        List<String> names = new ArrayList<>();
        books.forEach(book -> names.add(book.getName()));
        authorResponseDto.setBookNames(names);
        return  authorResponseDto;
    }

    public static List<AuthorResponseDto> authorsToAuthorResponseDtos(List<Author> authors){
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
         authors.forEach(author -> authorResponseDtos.add(authorToAuthorResponseDto(author)));
        return  authorResponseDtos;
    }

    public static CategoryResponseDto categoryToCategoryResponseDto(Category category){
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        List<String> names = new ArrayList<>();
        List<Book> books = category.getBooks();
        books.forEach(book -> names.add(book.getName()));
        categoryResponseDto.setBookNames(names);
        return  categoryResponseDto;
    }

    public static List<CategoryResponseDto> categoriesToCategoryResponseDtos (List<Category> categories){
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        categories.forEach(category -> categoryResponseDtos.add(categoryToCategoryResponseDto(category)));
        return  categoryResponseDtos;
    }
}
