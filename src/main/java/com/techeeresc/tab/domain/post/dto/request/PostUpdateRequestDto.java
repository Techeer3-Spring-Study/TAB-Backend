package com.techeeresc.tab.domain.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostUpdateRequestDto {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String file;
    private String image;
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.
    private boolean isAnonymous;
}
