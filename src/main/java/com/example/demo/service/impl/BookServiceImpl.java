package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ApiRequestError;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.mapper.BookDTOMapper;
import com.example.demo.model.dto.book.BookDTO;
import com.example.demo.model.dto.book.BookListDTO;
import com.example.demo.model.dto.book.BookPattern;
import com.example.demo.model.jpa.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.impl.AbstractServiceImpl;
import com.example.demo.utils.QueryBuilderHelper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class BookServiceImpl extends AbstractServiceImpl<Integer, Book, BookDTO, BookListDTO, BookPattern, BookRepository, BookDTOMapper> implements BookService {


	protected BookServiceImpl(BookRepository repository, BookDTOMapper mapper) {
		super(repository, mapper);
	}

	@Override
	protected void validate(Book entity, BookDTO dto) {
		List<ApiRequestError> errors = List.of();
		if (!errors.isEmpty()) {
			throw new ApiRequestException("User invalid", HttpStatus.BAD_REQUEST.value(), errors);
		}
	}

	@Override
	protected List<Predicate> getPredicates(BookPattern pattern, Root<Book> root, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		QueryBuilderHelper.propertyILikePredicate(criteriaBuilder, root, "name", pattern.getTitle()).ifPresent(predicates::add);
		return predicates;
	}
}
