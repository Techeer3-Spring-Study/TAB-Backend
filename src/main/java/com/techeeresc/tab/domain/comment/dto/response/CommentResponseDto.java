package com.techeeresc.tab.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {
  @Schema(description = "comment id", defaultValue = "1")
  private Long id;

  @Schema(description = "member id", defaultValue = "1")
  private Long memberId;

  @Schema(description = "post id", defaultValue = "1")
  private Long postId;

  @Schema(description = "comment id", defaultValue = "1")
  private Long commentId;

  @Schema(description = "comment content", defaultValue = "내용내용내용")
  private String content;

  @Schema(description = "comment layer", defaultValue = "1")
  private int layer; // TODO: 대댓글 관련 layer, 어떻게 처리할지 생각해봐야할듯

  @Schema(
      description = "anonymous check",
      allowableValues = {"true", "false"},
      defaultValue = "false")
  private boolean isAnonymous;
}
