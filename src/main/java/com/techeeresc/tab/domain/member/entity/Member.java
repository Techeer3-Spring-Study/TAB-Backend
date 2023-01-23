package com.techeeresc.tab.domain.member.entity;

import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import lombok.*;
//import org.springframework.context.annotation.Role;

import javax.persistence.*;

import com.techeeresc.tab.global.common.Timestamp;

import java.util.Collection;
import java.util.Set;
import com.techeeresc.tab.domain.member.entity.Authority;

@Entity
@AllArgsConstructor
@NoArgsConstructor //기본 생성자 추가
@Builder
@Getter
@Table(name="member")
public class Member extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 생성
    @Column(nullable = false, name ="member_id", columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "role")
    Role role;
//    private boolean role;
    @Column(columnDefinition = "boolean default true", name = "is_active")
    private boolean isActive;

    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        this.email = memberUpdateRequestDto.getEmail();
        this.password = memberUpdateRequestDto.getPassword();
        this.name = memberUpdateRequestDto.getName();

        return this;
    }

    @ManyToMany
    @JoinTable(
    name = "member_authority",
    joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
    inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
//멤버에서 권한을 바로! 테이블 2개는 너무 많이 생긴 것!