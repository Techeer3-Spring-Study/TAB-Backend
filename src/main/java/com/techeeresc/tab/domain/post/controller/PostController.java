package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PageRequest;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.service.PostService;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService POST_SERVICE;
    private final PostMapper POST_MAPPER;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostCreateRequestDto postCreateRequestDto) {
        Post insertPostResult = POST_SERVICE.insertPost(postCreateRequestDto);
        return new ResponseEntity(POST_MAPPER.getDataFromEntity(insertPostResult), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageImpl<Post>> findAllPosts(PageRequest pageRequest) {
        // return new ResponseEntity<>(POST_SERVICE.findAllPost(), HttpStatus.OK);
        Pageable pageable = pageRequest.of();
        PageImpl<Post> posts = POST_SERVICE.findAllPostListWithQueryDsl(pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostUpdateRequestDto postUpdateRequestDto) {
        Post updatePostResult = POST_SERVICE.updatePost(postUpdateRequestDto);
        return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(updatePostResult), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        POST_SERVICE.deletePost(id);
        return new ResponseEntity<>(StatusMessage.OK.getStatusMessage(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findPostAndIncreaseViews(@PathVariable Long id) {
        Post findPostResult = POST_SERVICE.findPostByIdAndIncreaseViews(id);
        return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(findPostResult), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PostResponseDto> increaseLikeNumbers(@PathVariable Long id) {
        Post clickLikePost = POST_SERVICE.increaseLikeNumbers(id);
        return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(clickLikePost), HttpStatus.CREATED);
    }

    @GetMapping("/search/{word:.+}")   /* PathVariable에 특수문자 허용 */
    public ResponseEntity<List<Post>> findPostSearchResults(@PathVariable String word) {
        List<Post> postSearchResults = POST_SERVICE.findByTitleContainsWordWithQueryDsl(word);
        return new ResponseEntity<>(postSearchResults, HttpStatus.OK);
    }


}
