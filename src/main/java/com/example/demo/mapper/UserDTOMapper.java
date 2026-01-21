package com.example.demo.mapper;

import com.example.demo.model.dto.user.UserDTO;
import com.example.demo.model.dto.user.UserListDTO;
import com.example.demo.model.jpa.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDTOMapper extends DTOGeneralMapper<UserDTO, UserListDTO, User> {

	@Override
	UserDTO toDTO(User user);

	@Override
	User toEntity(UserDTO userDTO);

	@Override
	UserListDTO toListDTO(User user);

	@Override
	void merge(UserDTO userDTO, @MappingTarget User user);
}
