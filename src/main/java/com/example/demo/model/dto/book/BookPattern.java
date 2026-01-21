package com.example.demo.model.dto.book;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookPattern implements Serializable {

	private Integer novelId;
	private String title;
}
