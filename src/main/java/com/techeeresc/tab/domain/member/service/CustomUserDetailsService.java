package com.techeeresc.tab.domain.member.service;

import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.exception.MemberNotFoundException;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository MEMBER_REPOSITORY;

    public CustomUserDetailsService(MemberRepository MEMBER_REPOSITORY) {
        this.MEMBER_REPOSITORY = MEMBER_REPOSITORY;
    }

    //사용자 + 권한 정보 반환
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        return MEMBER_REPOSITORY.findOneWithAuthoritiesByEmail(email)
                .map(member -> createUser(member.getEmail(), member))
                .orElseThrow(() -> new MemberNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND));
    }

    private org.springframework.security.core.userdetails.User createUser(String email, Member member) {
        if (!member.isActive()) {
            throw new RuntimeException(member.getName() + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(member.getName(),
                member.getPassword(),
                grantedAuthorities);
//        return null;
    }
}
