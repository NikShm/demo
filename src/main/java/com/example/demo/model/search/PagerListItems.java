package com.example.demo.model.search;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PagerListItems<T> {
	private long count;
	private List<T> items;

	public <R extends Serializable> PagerListItems<R> map(Function<T, ? extends R> mapper) {
		return new PagerListItems<>(count, items.stream().map(mapper).collect(Collectors.toList()));
	}
}
