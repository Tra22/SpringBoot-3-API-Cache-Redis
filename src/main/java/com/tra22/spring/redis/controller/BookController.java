package com.tra22.spring.redis.controller;

import com.tra22.spring.redis.dto.book.BookDto;
import com.tra22.spring.redis.service.interf.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
  private final IBookService bookService;
  @GetMapping
  public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(required = false) String title) {
      List<BookDto> books = new ArrayList<BookDto>();
      if (title == null)
          books.addAll(bookService.findAll());
      else
          books.addAll(bookService.findByTitleContaining(title));
      if (books.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getBookById(@PathVariable("id") long id) {
    return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BookDto> createBook(@RequestBody BookDto book) {
      return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> updateBook(@PathVariable("id") long id, @RequestBody BookDto book) {
      return new ResponseEntity<>(bookService.update(book), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
      bookService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> deleteAllBooks() {
      bookService.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/published")
  public ResponseEntity<List<BookDto>> findBookByPublished() {
      return new ResponseEntity<>(bookService.findByPublished(true), HttpStatus.OK);
  }
}
