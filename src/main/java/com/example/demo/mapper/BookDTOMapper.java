package com.example.demo.mapper;

import com.example.demo.model.dto.book.BookDTO;
import com.example.demo.model.dto.book.BookListDTO;
import com.example.demo.model.jpa.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookDTOMapper extends DTOGeneralMapper<BookDTO, BookListDTO, Book> {

	@Mapping(target = "translatorId", source = "translator.id")
	@Mapping(target = "novelId", source = "novel.id")
	@Override
	BookDTO toDTO(Book book);

	@Override
	BookListDTO toListDTO(Book book);

	@Mapping(target = "translator.id", source = "translatorId")
	@Mapping(target = "novel.id", source = "novelId")
	@Override
	Book toEntity(BookDTO bookDTO);

	@Mapping(target = "createdAt", ignore = true)
	@Override
	void merge(BookDTO bookDTO, @MappingTarget Book book);
}
