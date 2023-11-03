package com.tra22.spring.redis.repository;

import java.util.List;

import com.tra22.spring.redis.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByPublished(boolean published);

  List<Book> findByTitleContaining(String title);
}
