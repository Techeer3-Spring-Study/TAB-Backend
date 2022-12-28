package com.techeeresc.tab.domain.shareinfo.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Builder
public class ShareInfoCreateRequestDto{
    private String title;
    private String content;
    private String link;
    private String image;
    private String hashtag;
}
