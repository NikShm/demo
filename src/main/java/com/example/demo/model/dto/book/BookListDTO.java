package com.example.demo.model.dto.book;

import java.time.LocalDateTime;

import com.example.demo.model.EntityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookListDTO implements EntityDTO<Integer> {

	private Integer id;
	private String title;
	private Integer number;
	private LocalDateTime updateDatetime;

	@Override
	public Integer getId() {
		return id;
	}
}
