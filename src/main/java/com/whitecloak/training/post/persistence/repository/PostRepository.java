package com.whitecloak.training.post.persistence.repository;

import com.whitecloak.training.post.persistence.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends org.springframework.data.repository.Repository<PostEntity, Long> {

    PostEntity save(PostEntity entity);

    List<PostEntity> findAll();

    Page<PostEntity> findAll(Pageable pageable);

    Optional<PostEntity> findById(Long id);

    void deleteById(Long id);
}
