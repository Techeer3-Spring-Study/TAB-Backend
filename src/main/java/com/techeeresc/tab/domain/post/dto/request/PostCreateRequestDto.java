package com.techeeresc.tab.domain.post.dto.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateRequestDto {
    @NotNull
    private Long memberId;       // TODO: 외래키, 향후 외래키 매핑 필요, 토큰에서 받아온다.
    @NotNull
    private String category;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private String file;
    private String image;
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.
    @NotNull
    private boolean isAnonymous;
}
