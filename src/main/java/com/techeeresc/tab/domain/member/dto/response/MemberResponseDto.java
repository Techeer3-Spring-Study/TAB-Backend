package com.techeeresc.tab.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private boolean role;
    private boolean isActive;
}