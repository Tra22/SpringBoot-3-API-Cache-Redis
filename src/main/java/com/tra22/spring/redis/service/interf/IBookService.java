package com.tra22.spring.redis.service.interf;

import com.tra22.spring.redis.dto.book.BookDto;
import com.tra22.spring.redis.dto.book.CreateBookDto;
import com.tra22.spring.redis.dto.book.UpdateBookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll();
    List<BookDto> findByTitleContaining(String title);
    BookDto findById(long id);
    BookDto save(CreateBookDto book);
    BookDto update(UpdateBookDto book);
    void deleteById(long id);
    void deleteAll();
    List<BookDto> findByPublished(boolean isPublished);
}
