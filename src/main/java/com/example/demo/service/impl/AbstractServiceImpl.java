package com.example.demo.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import com.example.demo.mapper.DTOGeneralMapper;
import com.example.demo.model.Entity;
import com.example.demo.model.EntityDTO;
import com.example.demo.model.search.ListSearchData;
import com.example.demo.model.search.PagerListItems;
import com.example.demo.repository.EntityRepository;
import com.example.demo.service.BaseService;
import com.example.demo.utils.QueryBuilderHelper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Slf4j
public abstract class AbstractServiceImpl<I extends Serializable, E extends Entity<I>,  //
		D extends EntityDTO<I>, L extends Serializable, P extends Serializable,         //
		R extends EntityRepository<E, I>, M extends DTOGeneralMapper<D, L, E>>          //
		implements BaseService<I, D, L, P> {

	protected final R repository;
	protected final M mapper;

	protected AbstractServiceImpl(R repository, M mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public D getById(I id) {
//		LOGGER.debug("getById<{}>(id={})", dtoType, id);
		D dto = repository.findById(id).map(mapper::toDTO).orElse(null);
		if (dto != null) {
			postFetch(dto);
		}
		return dto;
	}

	@Override
	public PagerListItems<L> list(ListSearchData<P> listSearchData) {
//		LOGGER.debug("list<{}>(listSearchData={})", listDtoType, listSearchData);
		if (listSearchData.getOrders().isEmpty()) {
			listSearchData.addOrder("id");
		}
		Page<E> page = repository.findAll((root, _, criteriaBuilder) -> getPredicate(listSearchData.getSearchPattern(), root, criteriaBuilder),
				QueryBuilderHelper.toPageable(listSearchData));
		return new PagerListItems<>(page.getTotalElements(), mapper.toListDTOs(page.getContent()));
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public long count(P pattern) {
		return repository.count((root, _, criteriaBuilder) -> getPredicate(pattern, root, criteriaBuilder));
	}


	protected List<Predicate> getPredicates(P pattern, Root<E> root, CriteriaBuilder criteriaBuilder) {
		return Collections.emptyList();
	}

	private Predicate getPredicate(P pattern, Root<E> root, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = getPredicates(pattern, root, criteriaBuilder);
		return predicates.size() == 1 ? predicates.get(0) : criteriaBuilder.or(predicates.toArray(new Predicate[0]));
	}

	@Override
	public D save(D dto) {
//		LOGGER.debug("save<{}>(model.dto={})", dtoType, dto);
		E entity;
		if (dto.getId() == null) {
			entity = mapper.toEntity(dto);
		} else {
			entity = repository.findById(dto.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		}

		validate(entity, dto);

		beforeSave(entity, dto);

		mapper.merge(dto, entity);

		return mapper.toDTO(repository.save(entity));
	}

	@Override
	public void delete(I id) {
//		LOGGER.debug("delete<{}>(id={})", dtoType, id);
		repository.findById(id).ifPresent(entity -> {
			repository.deleteById(id);
			postDelete(entity);
		});
	}

	protected void postDelete(E entity) {
	}

	protected void postFetch(D dto) {
	}

	protected void validate(E entity, D dto) {
	}

	protected void beforeSave(E entity, D dto) {
	}
}
