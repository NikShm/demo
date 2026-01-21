package com.example.demo.repository;

import com.example.demo.model.jpa.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends EntityRepository<Book, Integer> {}
