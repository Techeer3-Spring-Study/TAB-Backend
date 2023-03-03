package com.techeeresc.tab.domain.member.controller;

import com.techeeresc.tab.domain.member.dto.mapper.MemberMapper;
import com.techeeresc.tab.domain.member.service.MemberService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import com.techeeresc.tab.global.status.StatusMessage;
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
public class MemberDeleteMethodController {
  private final MemberService MEMBER_SERVICE;
  private final MemberMapper MEMBER_MAPPER;

  @Operation(
      summary = "Delete Member",
      description = "해당 id 회원 삭제",
      responses = {@ApiResponse(responseCode = "200", description = "Delete Success")})
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public String deleteMember(
      @Parameter(description = "회원 id", in = ParameterIn.PATH) @PathVariable Long id) {
    MEMBER_SERVICE.deleteMember(id);
    return StatusMessage.OK.getStatusMessage();
  }
}
