package com.whitecloak.training.book.persistence.repository;

import com.whitecloak.training.book.persistence.entity.ReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends org.springframework.data.repository.Repository<ReviewEntity, Long> {

    ReviewEntity save(ReviewEntity entity);

    List<ReviewEntity> findAllByBookId(Long bookId);
}
