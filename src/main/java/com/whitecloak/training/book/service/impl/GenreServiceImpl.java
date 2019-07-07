package com.whitecloak.training.book.service.impl;

import com.whitecloak.training.book.persistence.entity.GenreEntity;
import com.whitecloak.training.book.persistence.repository.GenreRepository;
import com.whitecloak.training.book.response.GenreResource;
import com.whitecloak.training.book.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreResource> showAll() {
        return genreRepository.findAll()
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toList());
    }

    private GenreResource mapToResource(GenreEntity entity) {
        GenreResource resource = new GenreResource();
        resource.setId(entity.getId());
        resource.setName(entity.getName());
        return resource;
    }
}
