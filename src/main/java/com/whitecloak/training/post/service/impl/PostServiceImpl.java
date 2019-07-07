package com.whitecloak.training.post.service.impl;

import com.whitecloak.training.common.response.PaginatedResource;
import com.whitecloak.training.post.persistence.entity.PostEntity;
import com.whitecloak.training.post.persistence.repository.PostRepository;
import com.whitecloak.training.post.request.PostForm;
import com.whitecloak.training.post.response.PostResource;
import com.whitecloak.training.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

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
    public PaginatedResource<PostResource> showPaginated(Integer pageNumber, Integer size) {
        if (isNull(pageNumber)) {
            return showAll();
        }

        if (hasInvalidParams(pageNumber, size)) {
            return new PaginatedResource<>(emptyList(), 0, 0);  // An exception is usually thrown here
        }

        return paginateAll(pageNumber, size);
    }

    @Override
    public PostResource showOne(@PathVariable Long id) {
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

    private PaginatedResource<PostResource> paginateAll(Integer pageNumber, Integer size) {
        Pageable pageable = buildDefaultPageable(pageNumber - 1, size); // Page starts at zero here
        Page<PostEntity> page = postRepository.findAll(pageable);
        List<PostResource> resources = page.getContent()
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toList());
        return new PaginatedResource<>(resources, page.getTotalPages(), page.getTotalElements());
    }

    private boolean hasInvalidParams(Integer pageNumber, Integer size) {
        return isNull(size)
            || size < 1
            || pageNumber < 1;
    }

    private Pageable buildDefaultPageable(int pageNumber, int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "title");
        return PageRequest.of(pageNumber, size, sort);
    }

    private PaginatedResource<PostResource> showAll() {
        List<PostResource> resources = postRepository.findAll()
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toList());
        return new PaginatedResource<>(resources);
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
