package com.techeeresc.tab.domain.bookmark.dto.mapper;

import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {
  public Bookmark saveDataToEntity(BookmarkCreateRequestDto bookmarkCreateRequestDto) {
    return Bookmark.builder()
        .memberId(bookmarkCreateRequestDto.getMemberId())
        .postId(bookmarkCreateRequestDto.getPostId())
        .build();
  }

  public BookmarkResponseDto getDataFromEntity(Bookmark bookmark) {
    return BookmarkResponseDto.builder()
        .id(bookmark.getId())
        .memberId(bookmark.getMemberId())
        .postId(bookmark.getPostId())
        .build();
  }
}
