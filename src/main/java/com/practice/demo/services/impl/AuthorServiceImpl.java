package com.practice.demo.services.impl;

import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.entities.AuthorEntity;
import com.practice.demo.mappers.Mapper;
import com.practice.demo.repositories.AuthorRepository;
import com.practice.demo.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    public AuthorServiceImpl(Mapper<AuthorEntity, AuthorDto> authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);

        AuthorEntity savedAuthorEntity = authorRepository.save(authorEntity);

        AuthorDto authorDtoToBeReturned = authorMapper.mapTo(savedAuthorEntity);

        return authorDtoToBeReturned;
    }

    @Override
    public List<AuthorDto> findAll() {

        return authorRepository.findAll()
                .stream()
                .map(authorMapper :: mapTo)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<AuthorDto> findAuthorById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }
        Optional<AuthorEntity> foundAuthor = authorRepository.findById(id);
        return foundAuthor.map(author -> authorMapper.mapTo(author));
    }

    @Override
    public Boolean authorIsExist(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorDto updateById(Long id, AuthorDto authorDto) {
        authorDto.setId(id);
        AuthorEntity savedAuthor = authorRepository.save(authorMapper.mapFrom(authorDto));
        return authorMapper.mapTo(savedAuthor);
    }

    @Override
    public AuthorDto partialUpdate(Long id, AuthorDto authorDto) {

        Optional<AuthorEntity> partialUpdatedAuthor = authorRepository.findById(id);


        Optional.ofNullable(authorDto.getAge())
                .ifPresent(age -> partialUpdatedAuthor.ifPresent(a -> a.setAge(age)));

        Optional.ofNullable(authorDto.getName())
                .ifPresent(name -> partialUpdatedAuthor.ifPresent(a -> a.setName(name)));


        authorRepository.save(partialUpdatedAuthor.get());

        return authorMapper.mapTo(partialUpdatedAuthor.get());
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
