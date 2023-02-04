package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comment")
public class CommentController {
    private final CommentService COMMENT_SERVICE;
    private final CommentMapper COMMENT_MAPPER;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@PathVariable Long postId, @RequestBody CommentCreateRequestDto CommentCreateRequestDto) {
        Comment createCommentResult = COMMENT_SERVICE.createComment(postId, CommentCreateRequestDto);
        return COMMENT_MAPPER.getDataFromEntity(createCommentResult);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findAllComment() {
        return COMMENT_SERVICE.findAllComment();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto findComment(@PathVariable Long id) {
        Comment comment = COMMENT_SERVICE.findCommentById(id);
        return COMMENT_MAPPER.getDataFromEntity(comment);
    }

    @PutMapping
    public CommentResponseDto updateComment(@PathVariable Long postId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment updateCommentResult = COMMENT_SERVICE.updateComment(postId, commentUpdateRequestDto);
        return COMMENT_MAPPER.getDataFromEntity(updateCommentResult);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteComment(@PathVariable Long id) {
        COMMENT_SERVICE.deleteComment(id);
        return id;
    }
}