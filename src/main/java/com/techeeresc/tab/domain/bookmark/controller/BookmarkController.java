package com.techeeresc.tab.domain.bookmark.controller;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {
    private final BookmarkService BOOKMARK_SERVICES;
    private final BookmarkMapper BOOKMARK_MAPPER;
    @PostMapping // 서버로 데이터를 전송한다.
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkResponseDto createPost(@RequestBody BookmarkCreateRequestDto bookmarkCreateRequestDto) {
        Bookmark insertBookmarkResult = BOOKMARK_SERVICES.save(bookmarkCreateRequestDto);
        return BOOKMARK_MAPPER.getDataFromEntity(insertBookmarkResult);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> readAllComment() {
        return BOOKMARK_SERVICES.readAllComment();
    }
    @DeleteMapping("/{id}") //여기서부터 수정 진행 해주세요!
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> deleteBookmark(@PathVariable Long id) {
        List<Bookmark> bookmarks = BOOKMARK_SERVICES.deleteBookmark(id);
        return bookmarks;
    }
}
