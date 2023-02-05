package com.techeeresc.tab.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateRequestDto {
    @Schema(description = "member id", defaultValue = "1")
    private Long id;

    @Schema(description = "member pw", defaultValue = "tab")
    private String password;

    @Schema(description = "member name", defaultValue = "esc")
    private String name;
}