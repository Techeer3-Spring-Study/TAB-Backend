package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PageRequest;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.service.PostService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import com.techeeresc.tab.global.status.StatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class PostController {
  private final PostService POST_SERVICE;
  private final PostMapper POST_MAPPER;

  @Operation(summary = "create post", description = "Method: POST, success response code: 201")
  @ApiResponse(
      responseCode = "201",
      description = "CREATED",
      content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
  @PostMapping
  public ResponseEntity<PostResponseDto> createPost(
      @RequestBody @Valid PostCreateRequestDto postCreateRequestDto) {
    Post insertPostResult = POST_SERVICE.insertPost(postCreateRequestDto);
    return new ResponseEntity(POST_MAPPER.getDataFromEntity(insertPostResult), HttpStatus.CREATED);
  }

  @Operation(
      summary = "find all post by paging",
      description = "Method: GET, success response code: 200, parameter: ?page=&size=")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
  @GetMapping
  public ResponseEntity<List<Post>> findAllPosts(PageRequest pageRequest) {
    // return new ResponseEntity<>(POST_SERVICE.findAllPost(), HttpStatus.OK);
    Pageable pageable = pageRequest.of();
    List<Post> posts = POST_SERVICE.findAllPostListWithQueryDsl(pageable);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

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

  @Operation(
      summary = "delete post",
      description = "Method: DELETE, success response code: 200, 처리 완료 후 메인 화면 이동 필요")
  @ApiResponse(responseCode = "200", description = "OK")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(
      @Parameter(description = "페이지 아이디", in = ParameterIn.PATH) @PathVariable Long id) {
    POST_SERVICE.deletePost(id);
    return new ResponseEntity<>(StatusMessage.OK.getStatusMessage(), HttpStatus.OK);
  }

  @Operation(summary = "view one post", description = "Method: GET, success response code: 200")
  @ApiResponse(responseCode = "200", description = "OK")
  @GetMapping("/{id}")
  public ResponseEntity<PostResponseDto> findPostAndIncreaseViews(
      @Parameter(description = "페이지 아이디", in = ParameterIn.PATH) @PathVariable Long id) {
    Post findPostResult = POST_SERVICE.findPostByIdAndIncreaseViews(id);
    return new ResponseEntity<>(POST_MAPPER.getDataFromEntity(findPostResult), HttpStatus.OK);
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

  @Operation(
      summary = "search post by title",
      description = "Method: GET, success response code: 200")
  @ApiResponse(responseCode = "200", description = "OK")
  @GetMapping("/search/{word:.+}") /* PathVariable에 특수문자 허용 */
  public ResponseEntity<List<Post>> findPostSearchResults(
      @Parameter(description = "게시물 제목", in = ParameterIn.PATH) @PathVariable String word) {
    List<Post> postSearchResults = POST_SERVICE.findByTitleContainsWordWithQueryDsl(word);
    return new ResponseEntity<>(postSearchResults, HttpStatus.OK);
  }
}
