package com.techeeresc.tab.domain.bookmark.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BookmarkResponseDto {
    public Long id;
    public Long postId;
    public Long memberId;
}