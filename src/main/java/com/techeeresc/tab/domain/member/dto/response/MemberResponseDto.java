package com.techeeresc.tab.domain.member.dto.response;

import com.techeeresc.tab.domain.member.entity.Role;
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
    //private Role role;
    private boolean role;
    private boolean isActive;
}