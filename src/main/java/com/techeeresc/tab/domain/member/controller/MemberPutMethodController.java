package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.dto.request.MemberUpdateRequestDto;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MemberPutMethodController {
    private final MemberService MEMBER_SERVICE;
    private final MemberMapper MEMBER_MAPPER;

    @Operation(
            summary = "Update Member",
            description = "회원정보 수정",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update Success",
                            content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
            })
    @PutMapping
    public MemberResponseDto updateMember(
            @RequestBody MemberUpdateRequestDto MemberUpdateRequestDto) {
        Member updateMemberResult = MEMBER_SERVICE.updateMember(MemberUpdateRequestDto);
        return MEMBER_MAPPER.getDataFromEntity(updateMemberResult);
    }
}
