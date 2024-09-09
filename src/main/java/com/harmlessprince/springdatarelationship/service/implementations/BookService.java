package com.harmlessprince.springdatarelationship.service.implementations;

import com.harmlessprince.springdatarelationship.dtos.requests.BookRequestDto;
import com.harmlessprince.springdatarelationship.dtos.responseDtos.BookResponseDto;
import com.harmlessprince.springdatarelationship.exceptions.NotFoundException;
import com.harmlessprince.springdatarelationship.models.Author;
import com.harmlessprince.springdatarelationship.models.Book;
import com.harmlessprince.springdatarelationship.models.Category;
import com.harmlessprince.springdatarelationship.repositories.BookRepository;
import com.harmlessprince.springdatarelationship.service.interfaces.BookServiceInterface;
import com.harmlessprince.springdatarelationship.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {

        if (bookRequestDto.getAuthorIds().isEmpty()) {
            throw new IllegalArgumentException("A book needs at least one author");
        }
        if (bookRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("A book needs at least one category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());

        List<Author> authorList = new ArrayList<>();

        Book book = new Book();
        book.setName(bookRequestDto.getName());
        book.setCategory(category);
        bookRequestDto.getAuthorIds().forEach(authorId -> authorList.add(authorService.getAuthor(authorId)));
        book.setAuthors(authorList);

        Book createdBook = bookRepository.save(book);


        return MapperUtil.bookToBookResponseDto(createdBook);
    }

    @Override
    public Book getBook(Integer bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isEmpty()){
            throw new NotFoundException("Book", bookId);
        }

        return optionalBook.get();
    }

    @Override
    public BookResponseDto getBookById(Integer bookId) {
        Book book = getBook(bookId);
        return MapperUtil.bookToBookResponseDto(book);
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return MapperUtil.booksToBookResponseDtos(books);
    }

    @Override
    public void deleteBook(Integer bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return;
    }

    @Transactional
    @Override
    public BookResponseDto updateBook(Integer bookId, BookRequestDto bookRequestDto) {
        Book bookToEdit = getBook(bookId);
        if (!bookRequestDto.getAuthorIds().isEmpty()){
            List <Author> authorList = new ArrayList<>();
            bookRequestDto.getAuthorIds().forEach(authorId -> authorList.add(authorService.getAuthor(authorId)));
            bookToEdit.setAuthors(authorList);
        }
        if (bookRequestDto.getCategoryId() != null){
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        Book book = bookRepository.save(bookToEdit);
        return MapperUtil.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addAuthorToBook(Integer bookId, Integer authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (author.getBooks().contains(author)){
            throw new IllegalArgumentException("This author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return MapperUtil.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Integer bookId, Integer authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if (!(author.getBooks().contains(author))){
            throw new IllegalArgumentException("This author is not associated to this book");
        }
        author.removeBook(book);
        book.deleteAuthor(author);
        return MapperUtil.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Integer bookId, Integer categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (Objects.nonNull(book.getCategory())){
            throw  new IllegalArgumentException("Book already has a category");
        }
        book.setCategory(category);
        category.addBook(book);
        return MapperUtil.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeCategoryFromBook(Integer bookId, Integer categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if (!(Objects.nonNull(book.getCategory()))){
            throw  new IllegalArgumentException("Book doest not have a category to delete");
        }
        book.setCategory(null);
        category.removeBook(book);
        return MapperUtil.bookToBookResponseDto(book);
    }
}
