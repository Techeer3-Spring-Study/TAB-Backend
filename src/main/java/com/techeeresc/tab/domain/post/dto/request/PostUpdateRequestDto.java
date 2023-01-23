package com.techeeresc.tab.domain.post.dto.request;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Builder
@Getter
public class PostUpdateRequestDto {
    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    @Schema(description = "post id", defaultValue = "1")
    private Long id;
//    @NotNull
//    @Pattern(regexp = "^[0-9]*$")
//    @Schema(description = "user index id", defaultValue = "1")
//    private Long memberId;
    @NotNull
    @Schema(description = "post category", allowableValues = {"Frontend", "Backend", "DevOps", "etc"}, defaultValue = "Backend")
    private String category;
    @NotNull
    @Schema(description = "post title", defaultValue = "스프링 부트에 스웨거 설정하기")
    private String title;
    @NotNull
    @Schema(description = "post content", defaultValue = "스웨거 3.0을 통해 프론트엔드와 협업해보아요!")
    private String content;
    @Pattern(regexp = "/^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\\/\\/([a-z0-9-]+\\.)+[a-z0-9]{2,4}.*$/")
    @Schema(description = "AWS S3 file link", defaultValue = "www.s3file.com")
    private String file;
    @Pattern(regexp = "/^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\\/\\/([a-z0-9-]+\\.)+[a-z0-9]{2,4}.*$/")
    @Schema(description = "AWS S3 image link", defaultValue = "www.s3Image.com")
    private String image;
    @Schema(description = "post hashtags no blank", defaultValue = "#개발#프론트앤드#안녕")
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.
    @NotNull
    @Schema(description = "anonymous check", allowableValues = {"true", "false"}, defaultValue = "false")
    private boolean isAnonymous;
}
