package com.whitecloak.training.simple.persistence.repository;

import com.whitecloak.training.simple.persistence.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends org.springframework.data.repository.Repository<PostEntity, Long> {

    PostEntity save(PostEntity entity);

    List<PostEntity> findAll();

    Optional<PostEntity> findById(Long id);

    void deleteById(Long id);
}
