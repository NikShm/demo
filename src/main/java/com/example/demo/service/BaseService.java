package com.example.demo.service;

import java.io.Serializable;

import com.example.demo.model.EntityDTO;
import com.example.demo.model.search.ListSearchData;
import com.example.demo.model.search.PagerListItems;


public interface BaseService<I extends Serializable, D extends EntityDTO<I>, L, P extends Serializable> {
	D save(D entity);

	void delete(I entityId);

	D getById(I id);

	PagerListItems<L> list(ListSearchData<P> listSearchData);

	long count(P pattern);
}
