package com.techeeresc.tab.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class BookmarkResponseDto {
    public Long postId;
    public Long memberId;
}