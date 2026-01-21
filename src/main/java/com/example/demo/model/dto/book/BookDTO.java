package com.example.demo.model.dto.book;

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
public class BookDTO implements EntityDTO<Integer> {

	private Integer id;
	private Integer translatorId;
	private Integer novelId;
	private String title;
	private Integer number;

	@Override
	public Integer getId() {
		return id;
	}
}
