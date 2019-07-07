package com.whitecloak.training.simple.service;

import com.whitecloak.training.simple.request.PostForm;
import com.whitecloak.training.simple.response.PostResource;

import java.util.List;

public interface PostService {

    PostResource create(PostForm form);

    List<PostResource> fetchAll();

    PostResource fetchOne(Long id);

    PostResource update(Long id, PostForm form);

    void delete(Long id);
}
