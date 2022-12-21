package com.techeeresc.tab.domain.member.dto.request;

import com.techeeresc.tab.domain.member.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateRequestDto {

    private String email;

    private String password;

    private String name;

    private Role role;

    private boolean isActive;

}