package com.techeeresc.tab.domain.shareinfo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class ShareInfoUpdateRequestDto {

  @NotNull
  @Schema(description = "ShareInfo_id", defaultValue = "11")
  private Long id;

  @Schema(description = "ShareInfo_title", defaultValue = "LoL")
  private String title;

  @Schema(description = "ShareInfo_content", defaultValue = "WOW")
  private String content;

  @Schema(description = "ShareInfo_link", defaultValue = "www.naver.com")
  private String link;

  @Schema(description = "ShareInfo_image", defaultValue = "hello.jpg")
  private String image;

  @Schema(description = "ShareInfo_hashtag", defaultValue = "#wow")
  private String hashtag;
}
