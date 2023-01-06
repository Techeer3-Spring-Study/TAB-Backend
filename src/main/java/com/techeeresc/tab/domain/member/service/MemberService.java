package com.techeeresc.tab.domain.member.service;


import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberLoginRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.exception.MemberNotFoundException;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.global.exception.exceptionclass.BadRequestBodyException;
import com.techeeresc.tab.global.exception.exceptionclass.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MEMBER_REPOSITORY;
    private final MemberMapper MEMBER_MAPPER;

    @Transactional
    public Member signupMember(MemberCreateRequestDto memberCreateRequestDto) {
        if(MEMBER_REPOSITORY.findByEmail(memberCreateRequestDto.getEmail()).isPresent()){
                throw new BadRequestBodyException(StatusMessage.CONFLICT.getStatusMessage(), StatusCodes.CONFLICT);
        }
        return MEMBER_REPOSITORY.save(MEMBER_MAPPER.saveDataToEntity(memberCreateRequestDto));
    }

    @Transactional
    public Member loginMember(MemberLoginRequestDto memberLoginRequestDto) {
        return null;
    }


    @Transactional
    public Member deleteMember(Long id) {
//        try {
//            Member member = isMemberExisted(id);
//            MEMBER_REPOSITORY.deleteById(member.getId());
//        } catch(NullPointerException exception) {
//            throw new MemberNotFoundException("The member is not found.");
//        }
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND));
        MEMBER_REPOSITORY.deleteById(member.getId());

        return member;
    }

    @Transactional
    public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        try {
            Member member = isMemberExisted(memberUpdateRequestDto.getId());
            return member.updateMember(memberUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<Member> readAllMember() {
        return MEMBER_REPOSITORY.findAll();
    }

    @Transactional
    public MemberResponseDto findById(Long id){
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND));
        return MEMBER_MAPPER.getDataFromEntity(member);
    }

    public Member isMemberExisted(Long id) {
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND));
        return member;
    }



}