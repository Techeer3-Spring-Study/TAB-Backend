package com.techeeresc.tab.domain.member.dto.response;

import com.techeeresc.tab.domain.member.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDto {
  @Schema(description = "member id", defaultValue = "1")
  private Long id;

  @Schema(description = "member email", defaultValue = "tab@tab.com")
  private String email;

  @Schema(description = "member pw", defaultValue = "tab")
  private String password;

  @Schema(description = "member name", defaultValue = "esc")
  private String name;

  @Schema(
      description = "member role",
      allowableValues = {"true", "false"},
      defaultValue = "false") // role entity 추가되면서 여긴 수정될 예정!
  private Role role;

  @Schema(
      description = "active check",
      allowableValues = {"true", "false"},
      defaultValue = "true")
  private boolean isActive;
}