package com.whitecloak.training.post.service;

import com.whitecloak.training.common.response.PaginatedResource;
import com.whitecloak.training.post.request.PostForm;
import com.whitecloak.training.post.response.PostResource;

public interface PostService {

    PostResource create(PostForm form);

    PaginatedResource<PostResource> showPaginated(Integer pageNumber, Integer size);

    PostResource showOne(Long id);

    PostResource update(Long id, PostForm form);

    void delete(Long id);
}
