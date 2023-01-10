package com.techeeresc.tab.domain.shareinfo.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShareInfoResponseDto {
    private Long id;
    private String title;
    private String content;
    private String link;
    private String image;
    private String hashtag;
}