package com.practice.demo.services;

import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);

    List<AuthorDto> findAll();

    Optional<AuthorDto> findAuthorById(Long id);

    Boolean authorIsExist(Long id);

    AuthorDto updateById(Long id, AuthorDto authorDto);

    AuthorDto partialUpdate(Long id, AuthorDto authorDto);

    void deleteAuthorById(Long id);
}
