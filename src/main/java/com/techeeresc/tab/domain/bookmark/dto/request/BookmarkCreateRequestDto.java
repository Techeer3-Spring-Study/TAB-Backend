package com.techeeresc.tab.domain.bookmark.dto.request;

import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class BookmarkCreateRequestDto {

  @NotNull
  @Schema(description = "Post_ID", defaultValue = "1")
  private Long postId;

  @NotNull
  @Schema(
      description = "Member_ID",
      allowableValues = {"1"})
  private Long memberId;
}
