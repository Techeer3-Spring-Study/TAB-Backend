package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService MEMBER_SERVICE;
    private final MemberMapper MEMBER_MAPPER;

    //회원가입하기
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto createComment(@RequestBody MemberCreateRequestDto MemberCreateRequestDto) {
        Member insertMemberResult = MEMBER_SERVICE.createMember(MemberCreateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(insertMemberResult);
    }

    //회원 정보 가져오기
    @GetMapping("/{id}")
    public MemberResponseDto findById(@PathVariable Long id){
        return MEMBER_SERVICE.findById(id);
    }

    //로그인


}
