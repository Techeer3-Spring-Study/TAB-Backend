package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
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
public class MemberGetMethodController {
    private final MemberService MEMBER_SERVICE;
    private final MemberMapper MEMBER_MAPPER;

    @Operation(
            summary = "Find all Members",
            description = "모든 회원정보 가져오기",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Find Success",
                            content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Member> readAllMember() {
        return MEMBER_SERVICE.readAllMember();
    }

    @Operation(
            summary = "Find the Member",
            description = "해당 id 회원정보 가져오기",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Find Success",
                            content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto findMember(
            @Parameter(description = "회원 id", in = ParameterIn.PATH) @PathVariable Long id) {
        Member member = MEMBER_SERVICE.findMemberById(id);
        return MEMBER_MAPPER.getDataFromEntity(member);
    }
}
