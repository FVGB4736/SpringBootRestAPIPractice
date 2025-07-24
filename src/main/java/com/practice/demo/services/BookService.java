package com.practice.demo.services;

import com.practice.demo.domain.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDto createBook(String isbn, BookDto bookdto);

    List<BookDto> findAll();

    //使用分頁
    Page<BookDto> findAll(Pageable pageable);

    Optional<BookDto> findByIsbn(String isbn);

    BookDto PartialUpdateBook(String isbn, BookDto bookDto);

    void deleteBookByIsbn(String isbn);
}
