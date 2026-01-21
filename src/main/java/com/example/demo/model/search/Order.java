package com.example.demo.model.search;

import java.io.Serial;
import java.io.Serializable;

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
public class Order implements Serializable {
	@Serial
	private static final long serialVersionUID = -9170149545929237806L;

	public static final Order ORDER_BY_ID = Order.asc("id");

	private String propertyName;
	private boolean asc;

	public static Order asc(String propertyName) {
		return new Order(propertyName, true);
	}

	public static Order desc(String propertyName) {
		return new Order(propertyName, false);
	}
}
