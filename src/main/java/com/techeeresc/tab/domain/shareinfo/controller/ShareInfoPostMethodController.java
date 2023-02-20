package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.response.ShareInfoResponseDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiResponses({
  @ApiResponse(
      responseCode = "200",
      description = "OK !!",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  @ApiResponse(
      responseCode = "400",
      description = "BAD REQUEST !!",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  @ApiResponse(
      responseCode = "404",
      description = "NOT FOUND !!",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
  @ApiResponse(
      responseCode = "500",
      description = "INTERNAL SERVER ERROR !!",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
@Tag(name = "shareInfo", description = "ShareInfo API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shareinfo")
public class ShareInfoPostMethodController {

  private final ShareInfoService SHARE_INFO_SERVICE;
  private final ShareInfoMapper SHARE_INFO_MAPPER;

  @Operation(
      summary = "CreateShareInfo",
      description = "Create ShareInfo",
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Create Success",
            content = @Content(schema = @Schema(implementation = ShareInfoResponseDto.class))),
      })
  @PostMapping
  public ResponseEntity<ShareInfoResponseDto> createShareInfo(
      @RequestBody ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
    ShareInfo insertShareInfoResult = SHARE_INFO_SERVICE.insertShareInfo(shareInfoCreateRequestDto);
    return new ResponseEntity(
        SHARE_INFO_MAPPER.getDataFromEntity(insertShareInfoResult), HttpStatus.CREATED);
  }
}
