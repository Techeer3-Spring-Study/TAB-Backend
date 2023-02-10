package com.techeeresc.tab.domain.member.service;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberLoginRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
//import com.techeeresc.tab.domain.member.entity.Authority;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.entity.Role;
import com.techeeresc.tab.domain.member.exception.EmailDuplicateException;
import com.techeeresc.tab.domain.member.exception.EmailNotFoundException;
import com.techeeresc.tab.domain.member.exception.MemberNotFoundException;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import com.techeeresc.tab.global.exception.customexception.BadRequestBodyException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MEMBER_REPOSITORY;
    private final MemberMapper MEMBER_MAPPER;

    private final PasswordEncoder passwordEncoder;

    @Transactional
//    public Member signupMember(MemberCreateRequestDto memberCreateRequestDto) {
//        if (MEMBER_REPOSITORY.findByEmail(memberCreateRequestDto.getEmail()).isPresent()) {
//            throw new EmailDuplicateException(StatusMessage.CONFLICT.getStatusMessage(), StatusCodes.CONFLICT);
//        }
//        return MEMBER_REPOSITORY.save(MEMBER_MAPPER.saveDataToEntity(memberCreateRequestDto));
//    }
    public Member signupMember(MemberCreateRequestDto memberCreateRequestDto) {
        if (MEMBER_REPOSITORY.findOneWithAuthoritiesByEmail(memberCreateRequestDto.getEmail()).orElse(null) != null) {
            throw new EmailDuplicateException(StatusMessage.CONFLICT.getStatusMessage(), StatusCodes.CONFLICT);
        }

        Member member = Member.builder()
                .name(memberCreateRequestDto.getName())
                .password(passwordEncoder.encode(memberCreateRequestDto.getPassword()))
                .email(memberCreateRequestDto.getEmail())
                .role(Role.ROLE_USER)
                .build();

        return MEMBER_REPOSITORY.save(member);
    }

    @Transactional
    public Member loginMember(MemberLoginRequestDto memberLoginRequestDto) {   // TODO: if-else 대신 try-catch 사용하기
        Optional<Member> findByEmail = MEMBER_REPOSITORY.findByEmail(memberLoginRequestDto.getEmail());
        if (findByEmail.isPresent()) {
            //해당 이메일 존재
            Member member = findByEmail.get();
            if (!member.getPassword().equals(memberLoginRequestDto.getPassword())) {
                //비밀번호 틀렸을 때
                throw new BadRequestBodyException(StatusMessage.BAD_REQUEST_ABOUT_PASSWORD_MISMATCH.getStatusMessage(), StatusCodes.BAD_REQUEST);
            }
        } else {
            //해당 이메일 존재하지 않음
            throw new EmailNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
        return null; //TODO: 토큰으로 수정!
    }

    @Transactional
    public Member deleteMember(Long id) {
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(() ->
                new MemberNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND));
        MEMBER_REPOSITORY.deleteById(member.getId());
        return member;
    }

    @Transactional
    public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        try {
            Member member = isMemberExisted(memberUpdateRequestDto.getId());
            return member.updateMember(memberUpdateRequestDto);
        } catch (NullPointerException exception) {
            throw new MemberNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<Member> readAllMember() {
        return MEMBER_REPOSITORY.findAll();
    }

    @Transactional
    public Member findMemberById(Long id) {
        try {
            Member member = isMemberExisted(id);
            return member;
        } catch (NullPointerException exception) {
            throw new MemberNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    public Member isMemberExisted(Long id) {
        Member member = MEMBER_REPOSITORY.findById(id).orElseThrow(()
                -> new NullPointerException());
        return member;
    }
}