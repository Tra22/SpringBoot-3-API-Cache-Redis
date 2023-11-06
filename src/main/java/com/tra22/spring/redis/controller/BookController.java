package com.tra22.spring.redis.controller;

import com.tra22.spring.redis.dto.book.BookDto;
import com.tra22.spring.redis.dto.book.CreateBookDto;
import com.tra22.spring.redis.dto.book.UpdateBookDto;
import com.tra22.spring.redis.payload.global.Response;
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
  public ResponseEntity<Response<?>> getAllBooks(@RequestParam(required = false) String title) {
      List<BookDto> books = new ArrayList<BookDto>();
      if (title == null)
          books.addAll(bookService.findAll());
      else
          books.addAll(bookService.findByTitleContaining(title));
      if (books.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(Response.ok(books), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<?>> getBookById(@PathVariable("id") long id) {
    return new ResponseEntity<>(Response.ok(bookService.findById(id)), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Response<?>> createBook(@RequestBody CreateBookDto book) {
      return new ResponseEntity<>(Response.ok(bookService.save(book)), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Response<?>> updateBook(@PathVariable("id") long id, @RequestBody UpdateBookDto book) {
      return new ResponseEntity<>(Response.ok(bookService.update(book)), HttpStatus.OK);
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
  public ResponseEntity<Response<?>> findBookByPublished() {
      return new ResponseEntity<>(Response.ok(bookService.findByPublished(true)), HttpStatus.OK);
  }
}
