package com.practice.demo;

import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.dto.BookDto;
import com.practice.demo.domain.entities.AuthorEntity;
import com.practice.demo.domain.entities.BookEntity;

public class TestDataUtil {

    public TestDataUtil() {
    }

    public static AuthorDto createTestAuthorA(){

        return AuthorDto.builder()
                .id(1L)
                .name("AuthorA")
                .age(20)
                .build();
    }

    public static AuthorDto createTestAuthorB(){

        return AuthorDto.builder()
                .id(2L)
                .name("AuthorB")
                .age(20)
                .build();
    }

    public static AuthorDto createTestAuthorC(){

        return AuthorDto.builder()
                .id(1L)
                .name("AuthorC")
                .age(20)
                .build();
    }


    public static BookDto createTestBookA(final AuthorDto author){

        return BookDto.builder()
                .isbn("123")
                .title("BookA")
                .author(author)
                .build();
    }

    public static BookDto createTestBookB(final AuthorDto author){

        return BookDto.builder()
                .isbn("456")
                .title("BookB")
                .author(author)
                .build();
    }

    public static BookDto createTestBookC(final AuthorDto author){

        return BookDto.builder()
                .isbn("789")
                .title("BookC")
                .author(author)
                .build();
    }


}
