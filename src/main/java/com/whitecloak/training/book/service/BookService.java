package com.whitecloak.training.book.service;

import com.whitecloak.training.book.request.BookForm;
import com.whitecloak.training.book.response.BookResource;

import java.util.List;

public interface BookService {

    BookResource create(BookForm form);

    List<BookResource> showAll();

    BookResource showOne(Long id);
}
