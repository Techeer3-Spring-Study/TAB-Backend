package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService COMMENT_SERVICE;
    private final CommentMapper COMMENT_MAPPER;

    @PostMapping
    public CommentResponseDto createComment(@RequestBody CommentCreateRequestDto CommentCreateRequestDto) {
        Comment insertCommentResult = COMMENT_SERVICE.insertComment(CommentCreateRequestDto);
        return COMMENT_MAPPER.getDataFromEntity(insertCommentResult);
    }

    //responseentity는 http 리스펀스도 추가해야한다..! 좀 찾아보고 수정해 둘 것!
//    @GetMapping
//    public ResponseEntity<List<Comment>> readAllComment() {
//        return new ResponseEntity<>(COMMENT_SERVICE.readAllComment(), HttpStatus.OK);
//    }

    @PutMapping
    public CommentResponseDto updateComment(@RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment updateCommentResult = COMMENT_SERVICE.updateComment(commentUpdateRequestDto);
        return COMMENT_MAPPER.getDataFromEntity(updateCommentResult);
    }

    @DeleteMapping("/{id}")
    public List<Comment> deleteComment(@PathVariable Long id) {
        List<Comment> comments = COMMENT_SERVICE.deleteComment(id);

        return comments;
    }




}
