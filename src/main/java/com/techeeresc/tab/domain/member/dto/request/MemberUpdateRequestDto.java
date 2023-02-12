package com.techeeresc.tab.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class MemberUpdateRequestDto {
  @NotNull
  @Schema(description = "member id", defaultValue = "1")
  private Long id;

  @Schema(description = "member pw", defaultValue = "tab")
  private String password;

  @Schema(description = "member name", defaultValue = "esc")
  private String name;

  @Schema(
      description = "active check",
      allowableValues = {"true", "false"},
      defaultValue = "true")
  private boolean isActive;
}