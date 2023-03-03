package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.service.CommentService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ApiResponses({
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST by Parameter Missing",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST by Type Mismatch",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "404",
                description = "NOT FOUND",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "500",
                description = "INTERNAL SERVER ERROR",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
})
@Tag(name = "comment", description = "Comment API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/{postId}/comment")
public class CommentGetMethodController {
    private final CommentService COMMENT_SERVICE;
    private final CommentMapper COMMENT_MAPPER;

    @Operation(
            summary = "Find all Comments",
            description = "해당 게시물 모든 댓글 가져오기",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Find Success",
                            content = @Content(schema = @Schema(implementation = CommentResponseDto.class)))
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> findAllComment() {
        return COMMENT_SERVICE.findAllComment();
    }

}
