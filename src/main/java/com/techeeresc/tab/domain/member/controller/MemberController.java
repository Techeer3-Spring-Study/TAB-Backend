package com.techeeresc.tab.domain.member.controller;


import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberLoginRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService MEMBER_SERVICE;
    private final MemberMapper MEMBER_MAPPER;

    //회원가입하기
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto signupMember(@RequestBody MemberCreateRequestDto MemberCreateRequestDto) {
        Member signupMemberResult = MEMBER_SERVICE.signupMember(MemberCreateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(signupMemberResult);
    }

    //로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto loginMember(@RequestBody MemberLoginRequestDto MemberLoginRequestDto) {
        Member loginMemberResult = MEMBER_SERVICE.loginMember(MemberLoginRequestDto);
        if(loginMemberResult != null){
            return MEMBER_MAPPER.getDataFromEntity(loginMemberResult);
        }else{
            return null;
        }
    }

    //모든 회원 정보 가져오기
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Member> readAllMember() {
        return MEMBER_SERVICE.readAllMember();
    }

    //회원 정보 가져오기
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
     public MemberResponseDto findMember(@PathVariable Long id){
        Member member = MEMBER_SERVICE.findMemberById(id);
        return MEMBER_MAPPER.getDataFromEntity(member);
    }

    //회원 정보 수정
    @PutMapping
    public MemberResponseDto updateMember(@RequestBody MemberUpdateRequestDto MemberUpdateRequestDto) {
        Member updateMemberResult = MEMBER_SERVICE.updateMember(MemberUpdateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(updateMemberResult);
    }

    //회원 삭제 - 우선 delete처리함! security 적용 후 비활성화 처리하기
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> deleteMember(@PathVariable Long id) {
        List<Member> members = (List<Member>) MEMBER_SERVICE.deleteMember(id);

        return members;
    }

    //logout - 토큰 필요 -> 추후 개발할 것!

}