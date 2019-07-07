package com.whitecloak.training.book.service.impl;

import com.whitecloak.training.book.persistence.entity.BookEntity;
import com.whitecloak.training.book.persistence.entity.GenreEntity;
import com.whitecloak.training.book.persistence.repository.BookRepository;
import com.whitecloak.training.book.persistence.repository.GenreRepository;
import com.whitecloak.training.book.request.BookForm;
import com.whitecloak.training.book.response.BookResource;
import com.whitecloak.training.book.response.GenreResource;
import com.whitecloak.training.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public BookResource create(BookForm form) {
        BookEntity entity = mapToEntity(form);
        BookEntity savedEntity = bookRepository.save(entity);
        return mapToResource(savedEntity);
    }

    @Override
    public List<BookResource> showAll() {
        return bookRepository.findAll()
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toList());
    }

    @Override
    public BookResource showOne(Long id) {
        BookEntity entity = bookRepository.findOneById(id)
            .orElseGet((BookEntity::new));  // An exception is usually thrown here
        return mapToResource(entity);
    }

    private BookResource mapToResource(BookEntity entity) {
        BookResource resource = new BookResource();
        resource.setId(entity.getId());
        resource.setTitle(entity.getTitle());
        resource.setDescription(entity.getDescription());
        resource.setGenres(entity.getGenres()
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toSet()));
        return resource;
    }

    private GenreResource mapToResource(GenreEntity entity) {
        GenreResource resource = new GenreResource();
        resource.setId(entity.getId());
        resource.setName(entity.getName());
        return resource;
    }

    private BookEntity mapToEntity(BookForm form) {
        Set<GenreEntity> genres = form.getGenreIds()
            .stream()
            .map(this::fetchGenre)
            .collect(Collectors.toSet());

        BookEntity entity = new BookEntity();
        entity.setTitle(form.getTitle());
        entity.setDescription(form.getDescription());
        entity.setGenres(genres);

        return entity;
    }

    private GenreEntity fetchGenre(Long id) {
        return genreRepository.findOneById(id)
            .orElseGet(GenreEntity::new);    // An error is usually thrown here
    }
}
