package com.techeeresc.tab.domain.post.dto.request;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
@Builder
public class PostCreateRequestDto {
    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    @Schema(description = "user index id")
    private Long memberId;       // TODO: 외래키, 향후 외래키 매핑 필요, 토큰에서 받아온다.
    @NotNull
    @Schema(description = "post category", allowableValues = {"Frontend", "Backend", "DevOps", "etc"})
    private String category;
    @NotNull
    @Schema(description = "post title")
    private String title;
    @NotNull
    @Schema(description = "post content")
    private String content;
    @Pattern(regexp = "/^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\\/\\/([a-z0-9-]+\\.)+[a-z0-9]{2,4}.*$/")
    @Schema(description = "AWS S3 file link", allowableValues = "url")
    private String file;
    @Pattern(regexp = "/^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\\/\\/([a-z0-9-]+\\.)+[a-z0-9]{2,4}.*$/")
    @Schema(description = "AWS S3 image link", allowableValues = "url")
    private String image;
    @Schema(description = "post hashtags")
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.
    @NotNull
    @Schema(description = "anonymous check", allowableValues = {"true", "false"})
    private boolean isAnonymous;
}
