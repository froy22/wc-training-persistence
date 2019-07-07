package com.whitecloak.training.book.persistence.repository;

import com.whitecloak.training.book.persistence.entity.GenreEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends org.springframework.data.repository.Repository<GenreEntity, Long> {

    Optional<GenreEntity> findOneById(Long id);

    List<GenreEntity> findAll();
}
