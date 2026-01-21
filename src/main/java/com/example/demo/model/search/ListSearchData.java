package com.example.demo.model.search;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;

@Getter
@Setter
@ToString
public class ListSearchData<P extends Serializable> implements Serializable {
	@Serial
	private static final long serialVersionUID = -7322877525061797321L;
	private static final int DEFAULT_PAGE = 0;
	private static final int DEFAULT_PAGE_SIZE = 20;

	private int page = DEFAULT_PAGE;
	private int limit = DEFAULT_PAGE_SIZE;
	private List<Order> orders = new ArrayList<>();
	private P searchPattern;

	public ListSearchData() {
	}

	public ListSearchData(Integer page, Integer limit) {
		this.page = page;
		this.limit = limit;
	}

	public ListSearchData(Integer page, Integer limit, P searchPattern) {
		this(page, limit);
		this.searchPattern = searchPattern;
	}

	public ListSearchData(Integer page, Integer limit, List<Order> orders) {
		this(page, limit);
		if (orders != null) {
			this.orders = orders;
		}
	}

	public ListSearchData(Integer page, Integer limit, Order... orders) {
		this(page, limit);
		if (ArrayUtils.isNotEmpty(orders)) {
			this.orders = Arrays.asList(orders);
		}
	}

	public ListSearchData(ListSearchData<?> searchData) {
		this(searchData, null);
	}

	public ListSearchData(ListSearchData<?> searchData, P searchPattern) {
		this(searchData.getPage(), searchData.getLimit(), searchData.getOrders());
		this.searchPattern = searchPattern;
	}

	public ListSearchData(P searchPattern) {
		this.searchPattern = searchPattern;
	}

	public static <P extends Serializable> ListSearchData<P> of(Integer page, P pattern) {
		return of(page, DEFAULT_PAGE_SIZE, pattern);
	}

	public static <P extends Serializable> ListSearchData<P> of(Integer page, Integer limit, P pattern) {
		ListSearchData<P> searchData = new ListSearchData<>(page, limit);
		searchData.setSearchPattern(pattern);
		return searchData;
	}

	public int getFirstResult() {
		return page == -1 ? 0 : page * getLimit();
	}

	public int getMaxResults() {
		return limit < 0 ? Integer.MAX_VALUE : limit;
	}

	public void addOrder(String propertyName) {
		orders.add(Order.asc(propertyName));
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
}
