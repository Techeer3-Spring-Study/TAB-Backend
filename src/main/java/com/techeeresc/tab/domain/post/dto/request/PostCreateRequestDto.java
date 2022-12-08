package com.techeeresc.tab.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateRequestDto {
    private Long memberId;       // TODO: 외래키, 향후 외래키 매핑 필요, 토큰에서 받아온다.

    private String category;

    private String title;

    private String content;

    private String file;

    private String image;

    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.

    private boolean isAnonymous;

    private int likeNumbers;

    private int views;
}
