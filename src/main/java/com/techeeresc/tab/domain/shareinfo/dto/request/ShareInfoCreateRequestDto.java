package com.techeeresc.tab.domain.shareinfo.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShareInfoCreateRequestDto{
    private String title;
    private String content;
    private String link;
    private String image;
    private String hashtag;
}
