package com.whitecloak.training.book.controller;

import com.whitecloak.training.book.request.ReviewForm;
import com.whitecloak.training.book.response.ReviewResource;
import com.whitecloak.training.book.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/{bookId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewResource create(@PathVariable Long bookId, @RequestBody ReviewForm reviewForm) {
        return reviewService.create(bookId, reviewForm);
    }

    @GetMapping
    public List<ReviewResource> showAll(@PathVariable Long bookId) {
        return reviewService.showAll(bookId);
    }
}
