package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.service.PostService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
@Tag(name = "post", description = "Post API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostPostMethodController {
  private final PostService POST_SERVICE;
  private final PostMapper POST_MAPPER;

  @Operation(summary = "create post", description = "Method: POST, success response code: 201")
  @ApiResponse(
      responseCode = "201",
      description = "CREATED",
      content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
  @PostMapping
  public ResponseEntity<PostResponseDto> createPost(
      @RequestPart(value = "requestDto") @Valid PostCreateRequestDto postCreateRequestDto,
      @RequestPart(value = "files", required = false) List<MultipartFile> files) {

    Post insertPostResult = POST_SERVICE.insertPost(postCreateRequestDto, files);
    return new ResponseEntity<>(
        POST_MAPPER.getDataFromEntity(insertPostResult), HttpStatus.CREATED);
  }

  @Operation(
      summary = "increase like number",
      description = "Method: POST, success response code: 201")
  @ApiResponse(responseCode = "201", description = "CREATED")
  @PostMapping("/{id}")
  public ResponseEntity<PostResponseDto> increaseLikeNumbers(
      @Parameter(description = "페이지 아이디", in = ParameterIn.PATH) @PathVariable Long id) {
    Post clickLikePost = POST_SERVICE.increaseLikeNumbers(id);
    return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(clickLikePost), HttpStatus.CREATED);
  }
}
