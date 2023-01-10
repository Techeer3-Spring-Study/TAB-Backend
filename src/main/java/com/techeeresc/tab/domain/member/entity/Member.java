package com.techeeresc.tab.domain.member.entity;

import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import lombok.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.context.annotation.Role;

import javax.persistence.*;

import com.techeeresc.tab.global.common.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor //기본 생성자 추가
@Builder
@Setter
@Getter
@Table(name="member")
public class Member extends Timestamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 생성
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "role")
    //Role role;
    private boolean role;
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
        this.role = memberUpdateRequestDto.isRole(); //role도 업데이트되나요? 우선 넣어둘게요!

        return this;
    }
}