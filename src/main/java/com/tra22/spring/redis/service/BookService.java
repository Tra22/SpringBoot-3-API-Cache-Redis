package com.tra22.spring.redis.service;

import com.tra22.spring.redis.dto.book.BookDto;
import com.tra22.spring.redis.exception.NotFoundEntityException;
import com.tra22.spring.redis.mapper.book.IBookMapper;
import com.tra22.spring.redis.repository.BookRepository;
import com.tra22.spring.redis.service.interf.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
@RequiredArgsConstructor
public class BookService implements IBookService {
  private final BookRepository bookRepository;
  private final IBookMapper bookMapper;

  @Cacheable("books")
  public List<BookDto> findAll() {
    doLongRunningTask();
    return bookMapper.mapReverseList(bookRepository.findAll());
  }

  @Cacheable("books")
  public List<BookDto> findByTitleContaining(String title) {
    doLongRunningTask();
    return bookMapper.mapReverseList(bookRepository.findByTitleContaining(title));
  }

  @Cacheable("book")
  public BookDto findById(long id) {
    doLongRunningTask();
    return bookMapper.mapReverse(bookRepository.findById(id).orElseThrow(() -> new NotFoundEntityException("Not Found Book!")));
  }

  public BookDto save(BookDto book) {
    return bookMapper.mapReverse(bookRepository.save(bookMapper.map(book)));
  }

  @CacheEvict(value = "book", key = "#book.id")
  public BookDto update(BookDto book) {
    bookRepository.findById(book.getId()).orElseThrow(() -> new NotFoundEntityException("Not Found Book!"));
    return bookMapper.mapReverse(bookRepository.save(bookMapper.map(book)));
  }

  @CacheEvict(value = "book", key = "#id")
  public void deleteById(long id) {
    bookRepository.deleteById(id);
  }

  @CacheEvict(value = { "book", "books", "published_books" }, allEntries = true)
  public void deleteAll() {
    bookRepository.deleteAll();
  }

  @Cacheable("published_books")
  public List<BookDto> findByPublished(boolean isPublished) {
    doLongRunningTask();
    return bookMapper.mapReverseList(bookRepository.findByPublished(isPublished));
  }
  private void doLongRunningTask() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
