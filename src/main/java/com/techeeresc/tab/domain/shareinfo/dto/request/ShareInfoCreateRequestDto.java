package com.techeeresc.tab.domain.shareinfo.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShareInfoCreateRequestDto {
    private Long id;
    private String title;
    private String content;
    private Long link;
    private String image;
    private String hashtag;
}
