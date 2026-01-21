package com.example.demo.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.example.demo.model.CompareOperation;
import com.example.demo.model.Entity;
import com.example.demo.model.search.ListSearchData;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.example.demo.model.CompareOperation.*;

public class QueryBuilderHelper {
	private QueryBuilderHelper() {}

	public static <E extends Entity<? extends Serializable>> Optional<Predicate> propertyILikePredicate(CriteriaBuilder criteriaBuilder, Path<E> root, String property,
	                                                                                                    String value) {
		if (StringUtils.isNotEmpty(value)) {
			return Optional.of(ilike(root.get(property), criteriaBuilder, value));
		}
		return Optional.empty();
	}

	public static <E extends Entity<? extends Serializable>> Optional<Predicate> propertyEqualPredicate(CriteriaBuilder criteriaBuilder, Path<E> root, String property,
																										Object value) {
		if (value != null) {
			if (value instanceof String string && StringUtils.isEmpty(string)) {
				return Optional.empty();
			}
			return Optional.of(criteriaBuilder.equal(root.get(property), value));
		}
		return Optional.empty();
	}

	public static <E extends Entity<? extends Serializable>> Optional<Predicate> idPredicate(CriteriaBuilder criteriaBuilder, Root<E> root, String property, Integer value) {
		if (value != null) {
			return Optional.of(criteriaBuilder.equal(root.join(property).get("id"), value));
		}
		return Optional.empty();
	}

	public static Optional<Predicate> comparePredicate(CriteriaBuilder criteriaBuilder, Path<BigDecimal> field, CompareOperation operation, BigDecimal value) {
		if (value != null && operation != null) {
			return switch (operation) {
				case GT -> Optional.of(criteriaBuilder.greaterThan(field, value));
				case GT_EQ -> Optional.of(criteriaBuilder.greaterThanOrEqualTo(field, value));
				case EQ -> Optional.of(criteriaBuilder.equal(field, value));
				case LT -> Optional.of(criteriaBuilder.lessThan(field, value));
				case LT_EQ -> Optional.of(criteriaBuilder.lessThanOrEqualTo(field, value));
			};
		}
		return Optional.empty();
	}

	public static Optional<Predicate> dateIntervalPredicate(CriteriaBuilder criteriaBuilder, Path<LocalDateTime> field, LocalDate dateFrom, LocalDate dateTo) {
		if (dateFrom == null && dateTo == null) {
			return Optional.empty();
		}
		List<Predicate> datePredicates = new ArrayList<>();
		if (dateFrom != null) {
			datePredicates.add(criteriaBuilder.greaterThanOrEqualTo(field, dateFrom.atStartOfDay()));
		}
		if (dateTo != null) {
			datePredicates.add(criteriaBuilder.lessThanOrEqualTo(field, dateTo.atTime(LocalTime.MAX)));
		}
		return Optional.of(datePredicates.size() == 1 ? datePredicates.get(0) : criteriaBuilder.and(datePredicates.toArray(new Predicate[0])));
	}

	public static Optional<Predicate> dateIntervalPredicate(CriteriaBuilder criteriaBuilder, Path<LocalDateTime> field, LocalDateTime dateFrom, LocalDateTime dateTo) {
		if (dateFrom == null && dateTo == null) {
			return Optional.empty();
		}
		List<Predicate> datePredicates = new ArrayList<>();
		if (dateFrom != null) {
			datePredicates.add(criteriaBuilder.greaterThanOrEqualTo(field, dateFrom));
		}
		if (dateTo != null) {
			datePredicates.add(criteriaBuilder.lessThanOrEqualTo(field, dateTo));
		}
		return Optional.of(datePredicates.size() == 1 ? datePredicates.get(0) : criteriaBuilder.and(datePredicates.toArray(new Predicate[0])));
	}

	public static Predicate ilike(Path<String> path, CriteriaBuilder criteriaBuilder, String value) {
		return criteriaBuilder.like(criteriaBuilder.lower(path), '%' + value.toLowerCase(Locale.ROOT) + '%');
	}

	public static Predicate equal(Path<String> path, CriteriaBuilder criteriaBuilder, String value) {
		return criteriaBuilder.equal(path, value);
	}

	public static <T extends Serializable> Pageable toPageable(ListSearchData<T> searchOrder) {
		Sort sort;
		if (searchOrder.getOrders() != null && !searchOrder.getOrders().isEmpty()) {
			sort = Sort.by(searchOrder.getOrders().stream().map(ent -> new Sort.Order(ent.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, ent.getPropertyName())).toList());
		} else {
			sort = Sort.unsorted();
		}
		return PageRequest.of(searchOrder.getPage(), searchOrder.getLimit(), sort);
	}
}
