package com.techeeresc.tab.domain.shareinfo.dto.request;

import lombok.Builder;
@Builder
public class ShareInfoUpdateRequestDto {
        private Long id;
        private String title;
        private String content;
        private Long link;
        private String image;
        private String hashtag;
}
