package com.whitecloak.training.book.service.impl;

import com.whitecloak.training.book.persistence.entity.BookEntity;
import com.whitecloak.training.book.persistence.entity.ReviewEntity;
import com.whitecloak.training.book.persistence.repository.BookRepository;
import com.whitecloak.training.book.persistence.repository.ReviewRepository;
import com.whitecloak.training.book.request.ReviewForm;
import com.whitecloak.training.book.response.ReviewResource;
import com.whitecloak.training.book.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public ReviewResource create(Long bookId, ReviewForm form) {
        ReviewEntity entity = mapToEntity(form, bookId);
        ReviewEntity savedEntity = reviewRepository.save(entity);
        return mapToResource(savedEntity);
    }

    @Override
    public List<ReviewResource> showAll(Long bookId) {
        return reviewRepository.findAllByBookId(bookId)
            .stream()
            .map(this::mapToResource)
            .collect(Collectors.toList());
    }

    private ReviewResource mapToResource(ReviewEntity entity) {
        ReviewResource resource = new ReviewResource();
        resource.setId(entity.getId());
        resource.setTitle(entity.getTitle());
        resource.setContent(entity.getContent());
        return resource;
    }

    private ReviewEntity mapToEntity(ReviewForm form, Long bookId) {
        BookEntity book = bookRepository.findOneById(bookId)
            .orElseGet(BookEntity::new);    // An exception is usually thrown here

        ReviewEntity entity = new ReviewEntity();
        entity.setTitle(form.getTitle());
        entity.setContent(form.getContent());
        entity.setBook(book);

        return entity;
    }
}
