package com.techeeresc.tab.domain.bookmark.controller;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "OK !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "400",
                description = "BAD REQUEST !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "404",
                description = "NOT FOUND !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(
                responseCode = "500",
                description = "INTERNAL SERVER ERROR !!",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
@Tag(name = "bookmark", description = "Bookmark API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkPostMethodController {

    private final BookmarkService BOOKMARK_SERVICE;
    private final BookmarkMapper BOOKMARK_MAPPER;

    @Operation(
            summary = "CreateBookmark",
            description = "Create Bookmark",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Create Success",
                            content = @Content(schema = @Schema(implementation = BookmarkResponseDto.class))),
            })
    @PostMapping
    public ResponseEntity<BookmarkResponseDto> createBookmark(
            @RequestBody BookmarkCreateRequestDto bookmarkCreateRequestDto) {
        Bookmark insertBookmarkResult = BOOKMARK_SERVICE.save(bookmarkCreateRequestDto);
        return new ResponseEntity(BOOKMARK_MAPPER.getDataFromEntity(insertBookmarkResult), HttpStatus.CREATED);
    }


}
