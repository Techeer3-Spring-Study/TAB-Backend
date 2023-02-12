package com.techeeresc.tab.domain.member.dto.mapper;

import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member saveDataToEntity(MemberCreateRequestDto memberCreateRequestDto) {
        return Member.builder()
                .name(memberCreateRequestDto.getName())
                .password(memberCreateRequestDto.getPassword())
                .email(memberCreateRequestDto.getEmail())
                .build();
    }

    public MemberResponseDto getDataFromEntity(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .password(member.getPassword())
                .email(member.getEmail())
                .role(member.getRole())
                .isActive(member.isActive())
                .build();
    }
}