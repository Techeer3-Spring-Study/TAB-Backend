package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.service.PostService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
@Tag(name = "post", description = "Post API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostPutMethodController {
  private final PostService POST_SERVICE;
  private final PostMapper POST_MAPPER;

  @Operation(summary = "update post", description = "Method: PUT, success response code: 201")
  @ApiResponse(
      responseCode = "201",
      description = "CREATED",
      content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
  @PutMapping
  public ResponseEntity<PostResponseDto> updatePost(
      @RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto) {
    Post updatePostResult = POST_SERVICE.updatePost(postUpdateRequestDto);
    return new ResponseEntity<>(
        POST_MAPPER.getDataFromEntity(updatePostResult), HttpStatus.CREATED);
  }
}
