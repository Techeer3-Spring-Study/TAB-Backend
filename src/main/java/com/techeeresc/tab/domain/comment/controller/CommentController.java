package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comments")
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
    public List<Comment> readAllComment() {
        return COMMENT_SERVICE.readAllComment();
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
    public List<Comment> deleteComment(@PathVariable Long id) {
        List<Comment> comments = COMMENT_SERVICE.deleteComment(id);

        return comments;
    }

}
