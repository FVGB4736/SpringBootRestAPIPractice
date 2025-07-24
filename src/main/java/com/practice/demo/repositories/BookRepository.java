package com.practice.demo.repositories;

import com.practice.demo.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {



}
