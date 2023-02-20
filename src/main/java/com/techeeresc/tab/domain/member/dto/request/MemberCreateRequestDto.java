package com.techeeresc.tab.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class MemberCreateRequestDto {
  @NotNull
  @Schema(description = "member email", defaultValue = "tab@tab.com")
  private String email;

  @NotNull
  @Schema(description = "member pw", defaultValue = "tab")
  private String password;

  @NotNull
  @Schema(description = "member name", defaultValue = "esc")
  private String name;
}
