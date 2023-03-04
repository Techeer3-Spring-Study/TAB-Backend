package com.techeeresc.tab.domain.comment.dto.request;

import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class CommentCreateRequestDto {
  @NotNull
  @Schema(description = "member id", defaultValue = "1")
  private Long memberId;

  @NotNull
  @Schema(description = "post id", defaultValue = "1")
  private Long postId;

  @Schema(description = "comment id", defaultValue = "1")
  private Long commentId;

  @Schema(description = "comment content", defaultValue = "내용내용내용")
  private String content;

  @Schema(description = "comment layer", defaultValue = "1")
  private int layer;

  @Schema(
      description = "anonymous check",
      allowableValues = {"true", "false"},
      defaultValue = "false")
  private boolean isAnonymous;
}
