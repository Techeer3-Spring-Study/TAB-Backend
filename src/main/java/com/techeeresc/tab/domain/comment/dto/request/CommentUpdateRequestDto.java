package com.techeeresc.tab.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CommentUpdateRequestDto {
  @NotNull
  @Schema(description = "comment id", defaultValue = "1")
  private Long id;

  @Schema(description = "comment content", defaultValue = "내용내용내용")
  private String content;

  @Schema(
      description = "anonymous check",
      allowableValues = {"true", "false"},
      defaultValue = "false")
  private boolean isAnonymous;
}