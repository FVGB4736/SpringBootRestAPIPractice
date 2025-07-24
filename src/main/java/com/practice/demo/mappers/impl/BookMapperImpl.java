package com.practice.demo.mappers.impl;

import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.dto.BookDto;
import com.practice.demo.domain.entities.AuthorEntity;
import com.practice.demo.domain.entities.BookEntity;
import com.practice.demo.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {


    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
