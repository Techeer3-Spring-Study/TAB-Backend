package com.techeeresc.tab.domain.shareinfo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ShareInfoCreateRequestDto {

  @Schema(description = "ShareInfo_Title", defaultValue = "Hello")
  private String title;

  @Schema(description = "ShareInfo_Content", defaultValue = "Wow")
  private String content;

  @Schema(description = "ShareInfo_Link", defaultValue = "www.google.com")
  private String link;

  @Schema(description = "ShareInfo_image", defaultValue = "sad.jpg")
  private String image;

  @Schema(description = "ShareInfo_Hashtag", defaultValue = "#wow")
  private String hashtag;
}
