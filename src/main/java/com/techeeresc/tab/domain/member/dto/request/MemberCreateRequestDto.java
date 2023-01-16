package com.techeeresc.tab.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateRequestDto {
    private String email;
    private String password;
    private String name;
    //private Role role;
    private boolean role;
    private boolean isActive;
}