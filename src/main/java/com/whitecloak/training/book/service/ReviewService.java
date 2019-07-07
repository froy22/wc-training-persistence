package com.whitecloak.training.book.service;

import com.whitecloak.training.book.request.ReviewForm;
import com.whitecloak.training.book.response.ReviewResource;

import java.util.List;

public interface ReviewService {

    ReviewResource create(Long bookId, ReviewForm form);

    List<ReviewResource> showAll(Long bookId);
}
