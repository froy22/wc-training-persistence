package com.whitecloak.training.simple.controller;

import com.whitecloak.training.common.response.MessageResource;
import com.whitecloak.training.simple.request.PostForm;
import com.whitecloak.training.simple.response.PostResource;
import com.whitecloak.training.simple.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<PostResource> fetchAll() {
        return postService.fetchAll();
    }

    @GetMapping("/{id}")
    public PostResource fetchOne(@PathVariable Long id) {
        return postService.fetchOne(id);
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
