package com.techeeresc.tab.domain.bookmark.controller;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkPagingDto;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
    private final BookmarkService BOOKMARK_SERVICE;
    private final BookmarkMapper BOOKMARK_MAPPER;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkResponseDto createBookmark(@RequestBody BookmarkCreateRequestDto bookmarkCreateRequestDto) {
        Bookmark insertBookmarkResult = BOOKMARK_SERVICE.save(bookmarkCreateRequestDto);
        return BOOKMARK_MAPPER.getDataFromEntity(insertBookmarkResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkResponseDto> findBookmark(@PathVariable Long id) {
        Bookmark findBookmarkResult = BOOKMARK_SERVICE.findBookmarkById(id);
        return new ResponseEntity<>(BOOKMARK_MAPPER.getDataFromEntity(findBookmarkResult), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")    // TODO: 해당 부분도 반환타입이 리스트가 아님 확인
    @ResponseStatus(HttpStatus.OK)
    public Long BookmarkDelete(@PathVariable Long id) {
        BOOKMARK_SERVICE.deleteBookmark(id);
        return id;
    }

    @GetMapping
    public ResponseEntity<PageImpl<Bookmark>> findAllBookmark(BookmarkPagingDto bookmarkPagingDTO) {
        Pageable pageable = bookmarkPagingDTO.of();
        PageImpl<Bookmark> bookmarks = BOOKMARK_SERVICE.findAllBookmark(pageable);
        return new ResponseEntity<>(bookmarks, HttpStatus.OK);
    }
}

