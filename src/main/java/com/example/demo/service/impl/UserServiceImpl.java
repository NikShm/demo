package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.exception.ApiRequestError;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.mapper.UserDTOMapper;
import com.example.demo.model.dto.user.UserDTO;
import com.example.demo.model.dto.user.UserListDTO;
import com.example.demo.model.dto.user.UserPattern;
import com.example.demo.model.jpa.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
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
public class UserServiceImpl extends AbstractServiceImpl<Integer, User, UserDTO, UserListDTO, UserPattern, UserRepository, UserDTOMapper> implements UserService {


	protected UserServiceImpl(UserRepository repository, UserDTOMapper mapper) {
		super(repository, mapper);
	}

	@Override
	protected void validate(User entity, UserDTO dto) {
		List<ApiRequestError> errors = List.of();
		if (!errors.isEmpty()) {
			throw new ApiRequestException("User invalid", HttpStatus.BAD_REQUEST.value(), errors);
		}
	}

	@Override
	protected List<Predicate> getPredicates(UserPattern pattern, Root<User> root, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		QueryBuilderHelper.propertyILikePredicate(criteriaBuilder, root, "name", pattern.getName()).ifPresent(predicates::add);
		return predicates;
	}
}
