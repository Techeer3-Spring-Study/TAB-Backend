package com.techeeresc.tab.domain.member.service;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MEMBER_REPOSITORY;
    private final MemberMapper MEMBER_MAPPER;

    @Transactional
    public Member createMember(MemberCreateRequestDto memberCreateRequestDto) {
        return MEMBER_REPOSITORY.save(MEMBER_MAPPER.saveDataToEntity(memberCreateRequestDto));
    }

//    public MemberResponseDto findById(Long id) {
//        try {
//            Member member = isMemberExisted(id);
//            return member;
//        } catch (NullPointerException exception) {
//            throw new MemberNotFoundException("Member is not found.");
//        }
//    }

//    public Member isMemberExisted(Long id) {
//        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(() ->
//                new NullPointerException());
//
//        return member;
//    }
    @Transactional
    public MemberResponseDto findById(Long id){
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
//        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
//                -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return MEMBER_MAPPER.getDataFromEntity(member);
    }


}