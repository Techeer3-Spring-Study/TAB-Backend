package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
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
import org.springframework.web.bind.annotation.*;

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
public class CommentPutMethodController {
    private final CommentService COMMENT_SERVICE;
    private final CommentMapper COMMENT_MAPPER;

    @Operation(
            summary = "Update Comment",
            description = "댓글 수정",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update Success",
                            content = @Content(schema = @Schema(implementation = CommentResponseDto.class)))
            })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updateComment(
            @PathVariable Long postId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment updateCommentResult = COMMENT_SERVICE.updateComment(postId, commentUpdateRequestDto);
        return COMMENT_MAPPER.getDataFromEntity(updateCommentResult);
    }
}
