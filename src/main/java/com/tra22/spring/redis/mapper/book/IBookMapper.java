package com.tra22.spring.redis.mapper.book;

import com.tra22.spring.redis.dto.book.BookDto;
import com.tra22.spring.redis.dto.book.CreateBookDto;
import com.tra22.spring.redis.dto.book.UpdateBookDto;
import com.tra22.spring.redis.mapper.BaseMapper;
import com.tra22.spring.redis.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBookMapper extends BaseMapper<Book, BookDto> {
    Book mapCreateBook(CreateBookDto createBookDto);
    Book mapUpdateBook(UpdateBookDto updateBookDto);

}
