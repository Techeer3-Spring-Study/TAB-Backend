package com.techeeresc.tab.domain.bookmark.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookmarkResponseDto {
    public Long id;
    public Long postId;
    public Long memberId;
}