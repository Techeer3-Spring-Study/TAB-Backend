package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberCreateRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberLoginRequestDto;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
import com.techeeresc.tab.domain.member.dto.response.MemberResponseDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.service.MemberService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiResponses(
        {
                @ApiResponse(responseCode = "400", description = "BAD REQUEST by Parameter Missing",
                        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                ),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST by Type Mismatch",
                        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                ),
                @ApiResponse(responseCode = "404", description = "NOT FOUND",
                        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                ),
                @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                        content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                ),
        }
)
@Tag(name = "member", description = "Member API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService MEMBER_SERVICE;
    private final MemberMapper MEMBER_MAPPER;

    @Operation(summary = "Create Member", description = "회원가입", responses = {
            @ApiResponse(responseCode = "201", description = "Create Success", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))})
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto signupMember(@RequestBody MemberCreateRequestDto MemberCreateRequestDto) {
        Member signupMemberResult = MEMBER_SERVICE.signupMember(MemberCreateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(signupMemberResult);
    }

    @Operation(summary = "Login member", description = "로그인", responses = {
            @ApiResponse(responseCode = "200", description = "Login Success", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))})
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto loginMember(@RequestBody MemberLoginRequestDto MemberLoginRequestDto) {
        Member loginMemberResult = MEMBER_SERVICE.loginMember(MemberLoginRequestDto);
        if (loginMemberResult != null){
            return MEMBER_MAPPER.getDataFromEntity(loginMemberResult);
        } else {
            return null;
        }
    }

    @Operation(summary = "Find all Members", description = "모든 회원정보 가져오기", responses = {
            @ApiResponse(responseCode = "200", description = "Find Success", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Member> readAllMember() {
        return MEMBER_SERVICE.readAllMember();
    }

    @Operation(summary = "Find the Member", description = "해당 id 회원정보 가져오기", responses = {
            @ApiResponse(responseCode = "200", description = "Find Success", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto findMember(@Parameter(description = "회원 id", in = ParameterIn.PATH) @PathVariable Long id){
        Member member = MEMBER_SERVICE.findMemberById(id);
        return MEMBER_MAPPER.getDataFromEntity(member);
    }

    @Operation(summary = "Update Member", description = "회원정보 수정", responses = {
            @ApiResponse(responseCode = "200", description = "Update Success", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))})
    @PutMapping
    public MemberResponseDto updateMember(@RequestBody MemberUpdateRequestDto MemberUpdateRequestDto) {
        Member updateMemberResult = MEMBER_SERVICE.updateMember(MemberUpdateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(updateMemberResult);
    }

    @Operation(summary = "Delete Member", description = "해당 id 회원 삭제", responses = {
            @ApiResponse(responseCode = "200", description = "Delete Success")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMember(@Parameter(description = "회원 id", in = ParameterIn.PATH) @PathVariable Long id) {
        MEMBER_SERVICE.deleteMember(id);
        return id;
    }

    //TODO: logout - 토큰 필요
}