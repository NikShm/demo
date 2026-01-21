package com.example.demo.mapper;

import org.mapstruct.MappingTarget;

public interface DTOMapper<D, E> {

	D toDTO(E e);

	E toEntity(D d);

	void merge(D d, @MappingTarget E e);
}
