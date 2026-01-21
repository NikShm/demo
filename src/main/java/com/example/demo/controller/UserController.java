package com.example.demo.controller;

import com.example.demo.model.dto.user.UserDTO;
import com.example.demo.model.dto.user.UserListDTO;
import com.example.demo.model.dto.user.UserPattern;
import com.example.demo.model.search.ListSearchData;
import com.example.demo.model.search.PagerListItems;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController extends AbstractController<Integer, UserDTO, UserListDTO, UserPattern, UserService> {

	protected UserController(UserService service) {
		super(service);
	}

	@GetMapping("/byId/{id}")
	@Override
	public UserDTO getById(@PathVariable Integer id) {
		return super.getById(id);
	}

	@PostMapping("list")
	@Override
	public PagerListItems<UserListDTO> list(@RequestBody ListSearchData<UserPattern> listSearchData) {
		return super.list(listSearchData);
	}

	@PostMapping("count")
	@Override
	public Long count(@RequestBody UserPattern searchPattern) {
		return super.count(searchPattern);
	}

	@PostMapping("save")
	@Override
	public UserDTO save(@RequestBody UserDTO entity) {
		return super.save(entity);
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable Integer id) {
		super.delete(id);
	}
}
