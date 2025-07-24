package com.practice.demo.controllers;

import com.practice.demo.domain.dto.BookDto;
import com.practice.demo.domain.entities.BookEntity;
import com.practice.demo.mappers.Mapper;
import com.practice.demo.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PostMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn,
                                              @RequestBody BookDto bookDto){

        BookDto savedBookDto = bookService.createBook(isbn, bookDto);

        return new ResponseEntity<BookDto>(savedBookDto, HttpStatus.CREATED);

    }

//    原始寫法
//    @GetMapping(path = "/books")
//    public List<BookDto> listBooks(){
//        List<BookDto> bookDtos = bookService.findAll();
//        return bookDtos;
//    }

//    分頁使用
//    Spring 預期 HTTP 請求中的查詢參數遵循以下格式：
//    page：指定頁碼（從 0 開始），例如 page=0 表示第一頁。
//    size：指定每頁記錄數，例如 size=10 表示每頁 10 條記錄。
//    sort：指定排序方式，例如 sort=title,asc 表示按 title 字段升序排序。
//    示例請求：GET /books?page=1&size=20&sort=title,desc
    @GetMapping(path = "/books")
    public Page<BookDto> listBooks(Pageable pageble){
        Page<BookDto> bookDtos = bookService.findAll(pageble);
        return bookDtos;
    }




    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn){
        Optional<BookDto> foundBook = bookService.findByIsbn(isbn);

        if (foundBook.isPresent()) {
            return ResponseEntity.ok(foundBook.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto){
        Optional<BookDto> foundBook = bookService.findByIsbn(isbn);

        if (! foundBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookService.createBook(isbn, bookDto));

    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto){
        Optional<BookDto> foundBook = bookService.findByIsbn(isbn);

        if (! foundBook.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookService.PartialUpdateBook(isbn, bookDto));

    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
        bookService.deleteBookByIsbn(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
