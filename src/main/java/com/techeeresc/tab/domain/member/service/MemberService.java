package com.techeeresc.tab.domain.member.service;


import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.exception.MemberNotFoundException;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MEMBER_REPOSITORY;
    private final MemberMapper MEMBER_MAPPER;

    public Member isMemberExisted(Long id) {
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new MemberNotFoundException("The member is not found."));

        return member;
    }

    @Transactional
    public Member createMember(MemberCreateRequestDto memberCreateRequestDto) {
        return MEMBER_REPOSITORY.save(MEMBER_MAPPER.saveDataToEntity(memberCreateRequestDto));
    }

    @Transactional
    public List<Member> deleteMember(Long id) {
        try {
            Member member = isMemberExisted(id);
            MEMBER_REPOSITORY.deleteById(member.getId());
        } catch(NullPointerException exception) {
            throw new MemberNotFoundException("The member is not found.");
        }
    }

    @Transactional
    public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        try {
            Member member = isMemberExisted(memberUpdateRequestDto.getId());
            return member.updateMember(memberUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new MemberNotFoundException("The member is not found.");
        }
    }


    //    public MemberResponseDto findById(Long id) {
//        try {
//            Member member = isMemberExisted(id);
//            return member;
//        } catch (NullPointerException exception) {
//            throw new MemberNotFoundException("Member is not found.");
//        }
//    }
    @Transactional
    public MemberResponseDto findById(Long id){
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new MemberNotFoundException("The member is not found."));
        return MEMBER_MAPPER.getDataFromEntity(member);
    }


}