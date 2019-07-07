package com.whitecloak.training.book.service.impl;

import com.whitecloak.training.book.persistence.entity.BookEntity;
import com.whitecloak.training.book.persistence.entity.GenreEntity;
import com.whitecloak.training.book.persistence.repository.BookRepository;
import com.whitecloak.training.book.persistence.repository.GenreRepository;
import com.whitecloak.training.book.request.BookForm;
import com.whitecloak.training.book.response.BookResource;
import com.whitecloak.training.book.response.GenreResource;
import com.whitecloak.training.common.error.ApiException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private BookServiceImpl service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_shouldCreateEntity_whenGivenFormIsValid() {
        String title = "Sample Book";
        String description = "Sample Description";
        Long genreId = 1L;
        Set<Long> genreIds = Collections.singleton(genreId);

        BookForm form = new BookForm();
        form.setTitle(title);
        form.setDescription(description);
        form.setGenreIds(genreIds);

        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(genreId);
        when(genreRepository.findOneById(genreId))
            .thenReturn(Optional.of(genreEntity));

        Set<GenreEntity> genres = Collections.singleton(genreEntity);

        Long bookId = 1L;
        BookEntity savedEntity = new BookEntity();
        savedEntity.setId(bookId);
        savedEntity.setTitle(title);
        savedEntity.setDescription(description);
        savedEntity.setGenres(genres);
        when(bookRepository.save(any()))
            .thenReturn(savedEntity);

        BookResource result = service.create(form);
        Set<Long> resultGenreIds = result.getGenres()
            .stream()
            .map(GenreResource::getId)
            .collect(Collectors.toSet());

        assertEquals(result.getTitle(), title);
        assertEquals(result.getDescription(), description);
        assertEquals(resultGenreIds, genreIds);
    }

    @Test(expected = ApiException.class)
    public void create_shouldThrowAnError_whenGivenGenreIdIsInvalid() {
        Long genreId = 1L;
        Set<Long> genreIds = Collections.singleton(genreId);

        BookForm form = new BookForm();
        form.setGenreIds(genreIds);

        GenreEntity genreEntity = null;
        when(genreRepository.findOneById(genreId))
            .thenReturn(Optional.ofNullable(genreEntity));

        service.create(form);
    }

    @Test
    public void showOne_shouldReturnResource_whenGivenAValidId() {
        Long bookId = 1L;
        BookEntity entity = new BookEntity();
        entity.setId(bookId);

        when(bookRepository.findOneById(bookId))
            .thenReturn(Optional.of(entity));

        BookResource result = service.showOne(bookId);

        assertEquals(result.getId(), bookId);
    }

    @Test(expected = ApiException.class)
    public void showOne_shouldThrowAnException_whenGivenAndInvalidId() {
        BookEntity entity = null;

        when(bookRepository.findOneById(any()))
            .thenReturn(Optional.ofNullable(entity));

        service.showOne(any());
    }

}
