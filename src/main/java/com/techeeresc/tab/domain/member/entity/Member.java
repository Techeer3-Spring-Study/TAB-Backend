package com.techeeresc.tab.domain.member.entity;

import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.global.common.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@AllArgsConstructor
//@NoArgsConstructor // 기본 생성자 추가
@Builder
@Getter
@DynamicInsert // null값이면 기본값으로 들어감
@Table(name = "member")
public class Member extends Timestamp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
  @Column(name = "member_id", nullable = false, columnDefinition = "INT UNSIGNED")
  private Long id;

  @Column(nullable = false, name = "email")
  private String email;

  @Column(nullable = false, name = "password")
  private String password;

  @Column(nullable = false, name = "name")
  private String name;

  @Column(nullable = false, name = "role")
  @ColumnDefault("false")
  private boolean role;

  @Column(nullable = false, name = "is_active")
  @Builder.Default
  private boolean isActive = true;

  public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) {
    this.password = memberUpdateRequestDto.getPassword();
    this.name = memberUpdateRequestDto.getName();
    this.isActive = memberUpdateRequestDto.isActive();

    return this;
  }

  public Member() {}

}
