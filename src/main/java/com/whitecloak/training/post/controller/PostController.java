package com.whitecloak.training.post.controller;

import com.whitecloak.training.common.response.MessageResource;
import com.whitecloak.training.common.response.PaginatedResource;
import com.whitecloak.training.post.request.PostForm;
import com.whitecloak.training.post.response.PostResource;
import com.whitecloak.training.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResource create(@RequestBody PostForm postForm) {
        return postService.create(postForm);
    }

    @GetMapping
    public PaginatedResource<PostResource> showPaginated(
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "size", required = false) Integer size
    ) {
        return postService.showPaginated(page, size);
    }

    @GetMapping("/{id}")
    public PostResource showOne(@PathVariable Long id) {
        return postService.showOne(id);
    }

    @PutMapping("/{id}")
    public PostResource update(@PathVariable Long id, @RequestBody PostForm postForm) {
        return postService.update(id, postForm);
    }

    @DeleteMapping("/{id}")
    public MessageResource delete(@PathVariable Long id) {
        postService.delete(id);
        return new MessageResource("Successfully deleted post.");
    }
}
