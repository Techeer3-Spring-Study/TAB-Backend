package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService POST_SERVICE;
    private final PostMapper POST_MAPPER;

    @PostMapping
    public PostResponseDto createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        Post insertPostResult = POST_SERVICE.insertPost(postCreateRequestDto);
        return POST_MAPPER.getDataFromEntity(insertPostResult);
    }

    @GetMapping
    public List<Post> readAllPost() {
        return POST_SERVICE.readAllPost();
    }

    @PutMapping
    public PostResponseDto updatePost(@RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        Post updatePostResult = POST_SERVICE.updatePost(postUpdateRequestDto);
        return POST_MAPPER.getDataFromEntity(updatePostResult);
    }

    @DeleteMapping("/{id}")
    public List<Post> deletePost(@PathVariable Long id) {
        List<Post> posts = POST_SERVICE.deletePost(id);
        return posts;
    }

    @GetMapping("/{id}")
    public PostResponseDto readPost(@PathVariable Long id) {
        return POST_SERVICE.findPostByIdAndIncreaseViews(id);
    }

    @PostMapping("/{id}")
    public PostResponseDto increaseLikeNumbers(@PathVariable Long id) {
        Post post = POST_SERVICE.increaseLikeNumbers(id);
        return POST_MAPPER.getDataFromEntity(post);
    }
}
