package com.techeeresc.tab.domain.shareinfo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class ShareInfoCreateRequestDto {

  @NotNull
  @Schema(description = "ShareInfo_Title", defaultValue = "Hello")
  private String title;

  @NotNull
  @Schema(description = "ShareInfo_Content", defaultValue = "Wow")
  private String content;

  @NotNull
  @Schema(description = "ShareInfo_Link", defaultValue = "www.google.com")
  private String link;

  @NotNull
  @Schema(description = "ShareInfo_image", defaultValue = "sad.jpg")
  private String image;

  @NotNull
  @Schema(description = "ShareInfo_Hashtag", defaultValue = "#wow")
  private String hashtag;
}
