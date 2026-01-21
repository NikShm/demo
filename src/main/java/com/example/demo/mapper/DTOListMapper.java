package com.example.demo.mapper;

import java.util.Collection;
import java.util.List;

public interface DTOListMapper<D, E> {

	D toListDTO(E e);

	default List<D> toListDTOs(Collection<E> es) {
		return es.stream().map(this::toListDTO).toList();
	}
}
