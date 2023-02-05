package com.techeeresc.tab.domain.member.entity;

import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.global.common.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor //기본 생성자 추가
@Builder
@Getter
@Table(name="member")
public class Member extends Timestamp {
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
    private boolean role;
    @Column(nullable = false, name = "is_active")
    @ColumnDefault("true")
    private boolean isActive;

    public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
        this.password = memberUpdateRequestDto.getPassword();
        this.name = memberUpdateRequestDto.getName();

        return this;
    }
}