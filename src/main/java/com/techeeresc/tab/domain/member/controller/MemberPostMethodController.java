package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberLoginRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.service.MemberService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ApiResponses({
  @ApiResponse(
      responseCode = "400",
      description = "BAD REQUEST by Parameter Missing",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  @ApiResponse(
      responseCode = "400",
      description = "BAD REQUEST by Type Mismatch",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  @ApiResponse(
      responseCode = "404",
      description = "NOT FOUND",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  @ApiResponse(
      responseCode = "500",
      description = "INTERNAL SERVER ERROR",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
})
@Tag(name = "member", description = "Member API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberPostMethodController {
  private final MemberService MEMBER_SERVICE;
  private final MemberMapper MEMBER_MAPPER;

  @Operation(
      summary = "Create Member",
      description = "회원가입",
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Create Success",
            content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
      })
  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public MemberResponseDto signupMember(
      @RequestBody MemberCreateRequestDto MemberCreateRequestDto) {
    Member signupMemberResult = MEMBER_SERVICE.signupMember(MemberCreateRequestDto);
    return MEMBER_MAPPER.getDataFromEntity(signupMemberResult);
  }

  @Operation(
      summary = "Login member",
      description = "로그인",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Login Success",
            content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
      })
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public MemberResponseDto loginMember(@RequestBody MemberLoginRequestDto MemberLoginRequestDto) {
    Member loginMemberResult = MEMBER_SERVICE.loginMember(MemberLoginRequestDto);
    if (loginMemberResult != null) {
      return MEMBER_MAPPER.getDataFromEntity(loginMemberResult);
    } else {
      return null;
    }
  }
}
