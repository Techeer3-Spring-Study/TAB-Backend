package com.techeeresc.tab.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter     // 없으면 406 오류가 발생한다. 데이터는 정상적으로 들어가지만 응답을 하지 못해 postman에서 406 오류 발생
public class PostResponseDto {
    private Long id;
    private Long memberId;       // TODO: 외래키, 향후 외래키 매핑 필요
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
