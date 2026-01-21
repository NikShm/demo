package com.example.demo.model.dto.user;

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
public class UserListDTO implements EntityDTO<Integer> {

	private Integer id;
	private String name;
	private String email;
	private String bio;

	@Override
	public Integer getId() {
		return id;
	}
}
