package com.techeeresc.tab.domain.bookmark.controller;
import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkPagingDto;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.service.BookmarkService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "OK !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "400",
                description = "BAD REQUEST !!",
                content = @Content(schema  = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404",
                description = "NOT FOUND !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500",
                description = "INTERNAL SERVER ERROR !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
@Tag(name = "bookmark", description = "Bookmark API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
  private final BookmarkService BOOKMARK_SERVICE;
  private final BookmarkMapper BOOKMARK_MAPPER;

  @Operation(summary = "CreateBookmark", description = "Create Bookmark", responses = {
          @ApiResponse(responseCode = "201", description = "Create Success", content = @Content(schema = @Schema(implementation = BookmarkResponseDto.class))),
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookmarkResponseDto createBookmark(
          @RequestBody BookmarkCreateRequestDto bookmarkCreateRequestDto) {
    Bookmark insertBookmarkResult = BOOKMARK_SERVICE.save(bookmarkCreateRequestDto);
    return BOOKMARK_MAPPER.getDataFromEntity(insertBookmarkResult);
  }

  @Operation(summary = "Bookmark조회", description = "id를 이용해서 Bookmark를 조회합니다", responses = {
          @ApiResponse(responseCode = "200", description = "Bookmark조회 성공", content = @Content(schema = @Schema(implementation = BookmarkResponseDto.class))),
  })
  @GetMapping("/{id}")
  public ResponseEntity<BookmarkResponseDto> findBookmark(@PathVariable Long id) {
    Bookmark findBookmarkResult = BOOKMARK_SERVICE.findBookmarkById(id);
    return new ResponseEntity<>(
            BOOKMARK_MAPPER.getDataFromEntity(findBookmarkResult), HttpStatus.OK);
  }

  @Operation(summary = "Bookmark Delete", description = "id를 이용하여 bookmark를 삭제합니다", responses = {
          @ApiResponse(responseCode = "200", description = "Bookmark Delete", content = @Content(schema = @Schema(implementation = BookmarkResponseDto.class)))
  })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Long BookmarkDelete(@PathVariable Long id) {
    BOOKMARK_SERVICE.deleteBookmark(id);
    return id;
  }

  @Operation(summary = "findAllBookmark", description = "FindAllBookmarkId", responses = {
          @ApiResponse(responseCode = "200", description = "FindAllBookmarkId Success", content = @Content(schema = @Schema(implementation = BookmarkResponseDto.class))),
  })
  @GetMapping
  public ResponseEntity<PageImpl<Bookmark>> findAllBookmark(BookmarkPagingDto bookmarkPagingDTO) {
    Pageable pageable = bookmarkPagingDTO.of();
    PageImpl<Bookmark> bookmarks = BOOKMARK_SERVICE.findAllBookmark(pageable);
    return new ResponseEntity<>(bookmarks, HttpStatus.OK);
  }
}

