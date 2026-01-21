package com.example.demo.model.dto.user;

import java.time.LocalDateTime;

import com.example.demo.model.EntityDTO;
import com.example.demo.model.jpa.User;
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
public class UserDTO implements EntityDTO<Integer> {

	private Integer id;
	private String name;
	private String login;
	private String password;
	private String email;
	private String bio;
	private User.Role role;
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;

	@Override
	public Integer getId() {
		return id;
	}
}
