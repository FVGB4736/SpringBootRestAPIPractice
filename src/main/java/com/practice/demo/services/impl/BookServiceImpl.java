package com.practice.demo.services.impl;

import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.dto.BookDto;
import com.practice.demo.domain.entities.AuthorEntity;
import com.practice.demo.domain.entities.BookEntity;
import com.practice.demo.mappers.impl.AuthorMapperImpl;
import com.practice.demo.mappers.impl.BookMapperImpl;
import com.practice.demo.repositories.BookRepository;
import com.practice.demo.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private BookMapperImpl bookMapper;

    private AuthorMapperImpl authorMapper;


    public BookServiceImpl(BookRepository bookRepository, BookMapperImpl bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    public BookDto createBook(String isbn, BookDto bookdto) {

        bookdto.setIsbn(isbn);

        BookEntity bookEntity = bookMapper.mapFrom(bookdto);

        BookEntity savedBookEntity = bookRepository.save(bookEntity);

        BookDto bookDtoToBeReturned = bookMapper.mapTo(savedBookEntity);

        return bookDtoToBeReturned;
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper :: mapTo)
                .collect(Collectors.toList());
    }

    //分頁使用
    @Override
    public Page<BookDto> findAll(Pageable pageable) {

        Page bookDtoPages = bookRepository.findAll(pageable);

        return bookDtoPages;
    }

    @Override
    public Optional<BookDto> findByIsbn(String isbn) {


        if (isbn == null) {
            throw new IllegalArgumentException("isbn cannot be null");
        }
        Optional<BookEntity> foundBook = bookRepository.findById(isbn);
        return foundBook.map(book -> bookMapper.mapTo(book));
    }

    @Override
    public BookDto PartialUpdateBook(String isbn, BookDto bookDto) {
        Optional<BookEntity> partialUpdatedBook = bookRepository.findById(isbn);


        Optional.ofNullable(bookDto.getTitle())
                .ifPresent(title -> partialUpdatedBook.ifPresent(b -> b.setTitle(title)));

        Optional.ofNullable(bookDto.getAuthor())
                .ifPresent(author -> partialUpdatedBook.ifPresent(b -> b.setAuthor(authorMapper.mapFrom(author))));


        bookRepository.save(partialUpdatedBook.get());

        return bookMapper.mapTo(partialUpdatedBook.get());
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
