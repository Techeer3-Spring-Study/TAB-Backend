package com.techeeresc.tab.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    //implements GrantedAuthority

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

}