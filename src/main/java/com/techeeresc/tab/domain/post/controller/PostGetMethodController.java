package com.techeeresc.tab.domain.post.controller;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PageRequest;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Tag(name = "post", description = "Post API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostGetMethodController {
  private final PostService POST_SERVICE;
  private final PostMapper POST_MAPPER;

  @Operation(
      summary = "find all post by paging",
      description = "Method: GET, success response code: 200, parameter: ?page=&size=")
  @ApiResponse(
      responseCode = "200",
      description = "OK",
      content = @Content(schema = @Schema(implementation = PostResponseDto.class)))
  @GetMapping
  public ResponseEntity<List<Post>> findAllPosts(PageRequest pageRequest) {
    Pageable pageable = pageRequest.of();
    List<Post> posts = POST_SERVICE.findAllPostListWithQueryDsl(pageable);
    return new ResponseEntity<>(posts, HttpStatus.OK);
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
      summary = "search post by title",
      description = "Method: GET, success response code: 200, parameter: /{검색할 단어}?page=&size=")
  @ApiResponse(responseCode = "200", description = "OK")
  @GetMapping("/search/{word:.+}") /* PathVariable에 특수문자 허용 */
  public ResponseEntity<List<Post>> findPostSearchResults(
      @Parameter(description = "게시물 제목", in = ParameterIn.PATH) @PathVariable String word,
      PageRequest pageRequest) {
    Pageable pageable = pageRequest.of();
    List<Post> postSearchResults = POST_SERVICE.findByTitleContainsWordWithQueryDsl(word, pageable);
    return new ResponseEntity<>(postSearchResults, HttpStatus.OK);
  }
}
