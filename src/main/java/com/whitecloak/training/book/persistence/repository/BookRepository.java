package com.whitecloak.training.book.persistence.repository;

import com.whitecloak.training.book.persistence.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends org.springframework.data.repository.Repository<BookEntity, Long> {

    BookEntity save(BookEntity entity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOneById(Long id);
}
