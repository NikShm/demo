package com.example.demo.service;


import com.example.demo.model.dto.book.BookDTO;
import com.example.demo.model.dto.book.BookListDTO;
import com.example.demo.model.dto.book.BookPattern;

public interface BookService extends BaseService<Integer, BookDTO, BookListDTO, BookPattern> {}
