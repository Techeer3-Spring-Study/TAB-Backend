package com.techeeresc.tab.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter     // 없으면 406 오류가 발생한다. 데이터는 정상적으로 들어가지만 응답을 하지 못해 postman에서 406 오류 발생
public class PostResponseDto {
    @Schema(description = "post id", defaultValue = "1")
    private Long id;
    @Schema(description = "user index id", defaultValue = "1")
    private Long memberId;       // TODO: 외래키, 향후 외래키 매핑 필요
    @Schema(description = "post category", allowableValues = {"Frontend", "Backend", "DevOps", "etc"}, defaultValue = "Backend")
    private String category;
    @Schema(description = "post title", defaultValue = "스프링 부트에 스웨거 설정하기")
    private String title;
    @Schema(description = "post content", defaultValue = "스웨거 3.0을 통해 프론트엔드와 협업해보아요!")
    private String content;
    @Schema(description = "AWS S3 file link", defaultValue = "www.s3file.com")
    private String file;
    @Schema(description = "AWS S3 image link", defaultValue = "www.s3Image.com")
    private String image;
    @Schema(description = "post hashtags no blank", defaultValue = "#개발#프론트앤드#안녕")
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.
    @Schema(description = "anonymous check", allowableValues = {"true", "false"}, defaultValue = "false")
    private boolean isAnonymous;
    @Schema(description = "like numbers", defaultValue = "0")
    private int likeNumbers;
    @Schema(description = "views", defaultValue = "0")
    private int views;
}
