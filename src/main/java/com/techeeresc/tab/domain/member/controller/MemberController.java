package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
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

    //회원 정보 수정
    @PutMapping
    public MemberResponseDto updateMember(@RequestBody MemberUpdateRequestDto MemberUpdateRequestDto) {
        Member updateMemberResult = MEMBER_SERVICE.updateMember(MemberUpdateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(updateMemberResult);
    }

    //회원 삭제
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Member> deleteMember(@PathVariable Long id) {
        List<Member> members = MEMBER_SERVICE.deleteMember(id);

        return members;
    }


}
