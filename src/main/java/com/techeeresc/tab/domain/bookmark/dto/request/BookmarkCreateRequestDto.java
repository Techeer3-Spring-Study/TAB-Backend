package com.techeeresc.tab.domain.bookmark.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class BookmarkCreateRequestDto {
    public Long postId;
    public Long memberId;
}