package com.whitecloak.training.simple.service.impl;

import com.whitecloak.training.simple.persistence.entity.PostEntity;
import com.whitecloak.training.simple.persistence.repository.PostRepository;
import com.whitecloak.training.simple.request.PostForm;
import com.whitecloak.training.simple.response.PostResource;
import com.whitecloak.training.simple.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResource create(PostForm form) {
        PostEntity entity = mapToEntity(form);
        PostEntity savedEntity = postRepository.save(entity);
        return mapToResource(savedEntity);
    }

    @Override
    public List<PostResource> fetchAll() {
        return postRepository.findAll()
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toList());
    }

    @Override
    public PostResource fetchOne(@PathVariable Long id) {
        PostEntity entity = postRepository.findById(id)
            .orElseGet(PostEntity::new);    // An exception is usually thrown if the entity is not found
        return mapToResource(entity);
    }

    @Override
    public PostResource update(Long id, PostForm form) {
        PostEntity entity = postRepository.findById(id)
            .orElseGet(PostEntity::new);    // An exception is usually thrown if the entity is not found

        entity.setTitle(form.getTitle());
        entity.setDescription(form.getDescription());

        PostEntity updatedEntity = postRepository.save(entity);
        return mapToResource(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private PostResource mapToResource(PostEntity entity) {
        PostResource resource = new PostResource();
        resource.setId(entity.getId());
        resource.setTitle(entity.getTitle());
        resource.setDescription(entity.getDescription());
        return resource;
    }

    private PostEntity mapToEntity(PostForm form) {
        PostEntity entity = new PostEntity();
        entity.setTitle(form.getTitle());
        entity.setDescription(form.getDescription());
        return entity;
    }
}
