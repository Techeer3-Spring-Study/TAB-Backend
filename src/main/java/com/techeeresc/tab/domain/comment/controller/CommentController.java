package com.techeeresc.tab.domain.comment.controller;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.service.CommentService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

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
public class CommentController {
  private final CommentService COMMENT_SERVICE;
  private final CommentMapper COMMENT_MAPPER;

  @Operation(
      summary = "Create Comment",
      description = "해당 게시물에 댓글 생성",
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Create Success",
            content = @Content(schema = @Schema(implementation = CommentResponseDto.class)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponseDto createComment(
      @PathVariable Long postId, @RequestBody CommentCreateRequestDto CommentCreateRequestDto) {
    Comment createCommentResult = COMMENT_SERVICE.createComment(postId, CommentCreateRequestDto);
    return COMMENT_MAPPER.getDataFromEntity(createCommentResult);
  }

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

  @Operation(
      summary = "Delete Comment",
      description = "해당 id 댓글 삭제",
      responses = {@ApiResponse(responseCode = "200", description = "Delete Success")})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Long deleteComment(
      @Parameter(description = "댓글 id", in = ParameterIn.PATH) @PathVariable Long id) {
    COMMENT_SERVICE.deleteComment(id);
    return id;
  }
}