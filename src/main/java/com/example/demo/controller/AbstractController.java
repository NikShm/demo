package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.EntityDTO;
import com.example.demo.model.search.ListSearchData;
import com.example.demo.model.search.PagerListItems;
import com.example.demo.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
abstract class AbstractController<I extends Serializable, D extends EntityDTO<I>, L, P extends Serializable, S extends BaseService<I, D, L, P>> {
	protected static final String WRONG_OBJECT = "Wrong object";

	protected final String dtoType;
	protected final String listDtoType;
	protected final S service;

	protected AbstractController(S service) {
		this.service = service;
		Type[] argumentTypes = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		this.dtoType = ((Class<?>) argumentTypes[1]).getSimpleName();
		this.listDtoType = ((Class<?>) argumentTypes[2]).getSimpleName();
	}

	public D save(D entity) {
//		LOGGER.debug("save<{}>(entity={})", dtoType, entity);
		if (entity == null) {
			throw new ApiRequestException(WRONG_OBJECT, HttpStatus.BAD_REQUEST.value());
		}
		return service.save(entity);
	}

	public void delete(I id) {
//		LOGGER.debug("delete<{}>(id={})", dtoType, id);
		if (id == null) {
			throw new ApiRequestException(WRONG_OBJECT, HttpStatus.BAD_REQUEST.value());
		}
		service.delete(id);
	}

	public D getById(I id) {
//		LOGGER.debug("getById<{}>(id={})", dtoType, id);
		if (id == null) {
			throw new ApiRequestException(WRONG_OBJECT, HttpStatus.BAD_REQUEST.value());
		}
		return service.getById(id);
	}

	public PagerListItems<L> list(ListSearchData<P> listSearchData) {
//		LOGGER.debug("list<{}>(listSearchData={})", listDtoType, listSearchData);
		return service.list(listSearchData);
	}

	public Long count(P searchPattern) {
//		LOGGER.debug("list<{}>(searchPattern={})", listDtoType, searchPattern);
		return service.count(searchPattern);
	}
}
