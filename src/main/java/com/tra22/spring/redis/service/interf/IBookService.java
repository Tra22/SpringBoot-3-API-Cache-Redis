package com.tra22.spring.redis.service.interf;

import com.tra22.spring.redis.dto.book.BookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll();
    List<BookDto> findByTitleContaining(String title);
    BookDto findById(long id);
    BookDto save(BookDto book);
    BookDto update(BookDto book);
    void deleteById(long id);
    void deleteAll();
    List<BookDto> findByPublished(boolean isPublished);
}
