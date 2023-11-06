package com.tra22.spring.redis.mapper.book;

import com.tra22.spring.redis.dto.book.BookDto;
import com.tra22.spring.redis.dto.book.CreateBookDto;
import com.tra22.spring.redis.dto.book.UpdateBookDto;
import com.tra22.spring.redis.mapper.BaseMapper;
import com.tra22.spring.redis.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IBookMapper extends BaseMapper<Book, BookDto> {
    @Mappings({
            @Mapping(target = "published",  expression = "java(true)")
    })
    Book mapCreateBook(CreateBookDto createBookDto);
    Book mapUpdateBook(UpdateBookDto updateBookDto);

}
