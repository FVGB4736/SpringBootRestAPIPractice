package com.practice.demo.controllers;

import com.practice.demo.domain.dto.AuthorDto;
import com.practice.demo.domain.entities.AuthorEntity;
import com.practice.demo.mappers.Mapper;
import com.practice.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;

    @Autowired
    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto){
        AuthorDto savedAuthorDto = authorService.createAuthor(authorDto);

        //ResponseEntity可回傳第二個參數HttpStatus.CREATED，將原本200改為201
        return new ResponseEntity<>(savedAuthorDto, HttpStatus.CREATED);

    }

    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors(){
        List<AuthorDto> authors = authorService.findAll();
        return authors;
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id){
        Optional<AuthorDto> foundAuthor = authorService.findAuthorById(id);

        if (foundAuthor.isPresent()) {
            return ResponseEntity.ok(foundAuthor.get());
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto){
        Boolean authorExist = authorService.authorIsExist(id);

        if(! authorExist){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(authorService.updateById(id, authorDto));
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> partialUpdateAuthor(@PathVariable("id") Long id, @RequestBody com.practice.demo.domain.dto.AuthorDto authorDto){

        Boolean authorExist = authorService.authorIsExist(id);

        if(! authorExist){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(authorService.partialUpdate(id, authorDto));

    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id ){
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
