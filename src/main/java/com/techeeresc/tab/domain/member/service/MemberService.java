package com.techeeresc.tab.domain.member.service;

import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberLoginRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.domain.member.entity.Member;

import java.util.List;


public interface MemberService {
  public Member signupMember(MemberCreateRequestDto memberCreateRequestDto);
  public Member loginMember(MemberLoginRequestDto memberLoginRequestDto);
  public Member deleteMember(Long id);
  public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto);
  public List<Member> readAllMember();
  public Member findMemberById(Long id);
}