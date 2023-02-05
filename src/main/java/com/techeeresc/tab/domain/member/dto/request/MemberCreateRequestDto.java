package com.techeeresc.tab.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateRequestDto {
    @Schema(description = "member email", defaultValue = "tab@tab.com")
    private String email;

    @Schema(description = "member pw", defaultValue = "tab")
    private String password;

    @Schema(description = "member name", defaultValue = "esc")
    private String name;

    @Schema(description = "active check", allowableValues = {"true", "false"}, defaultValue = "true")
    private boolean isActive;
}