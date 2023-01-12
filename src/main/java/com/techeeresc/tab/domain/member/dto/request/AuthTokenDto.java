package com.techeeresc.tab.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthTokenDto {
    private String token;
}
