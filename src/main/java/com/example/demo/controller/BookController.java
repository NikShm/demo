package com.example.demo.controller;

import com.example.demo.model.dto.book.BookDTO;
import com.example.demo.model.dto.book.BookListDTO;
import com.example.demo.model.dto.book.BookPattern;
import com.example.demo.model.search.ListSearchData;
import com.example.demo.model.search.PagerListItems;
import com.example.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4100")
@Slf4j
public class BookController extends AbstractController<Integer, BookDTO, BookListDTO, BookPattern, BookService> {

	protected BookController(BookService service) {
		super(service);
	}

	@GetMapping("/byId/{id}")
	@Override
	public BookDTO getById(@PathVariable Integer id) {
		return super.getById(id);
	}

	@PostMapping("list")
	@Override
	public PagerListItems<BookListDTO> list(@RequestBody ListSearchData<BookPattern> listSearchData) {
		return super.list(listSearchData);
	}

	@PostMapping("count")
	@Override
	public Long count(@RequestBody BookPattern searchPattern) {
		return super.count(searchPattern);
	}

	@PostMapping("save")
	@Override
	public BookDTO save(@RequestBody BookDTO entity) {
		return super.save(entity);
	}

	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable Integer id) {
		super.delete(id);
	}
}
