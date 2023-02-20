package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoPagingDto;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ShareInfoGetMethodController {

  private final ShareInfoService SHARE_INFO_SERVICE;
  private final ShareInfoMapper SHARE_INFO_MAPPER;

  @Operation(
      summary = "findAllShareInfo",
      description = "findAll ShareInfo",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "findAll Success",
            content = @Content(schema = @Schema(implementation = ShareInfoResponseDto.class))),
      })
  @GetMapping
  public ResponseEntity<PageImpl<ShareInfo>> findAllShareInfo(
      ShareInfoPagingDto shareInfoPagingDto) {
    Pageable pageable = shareInfoPagingDto.of();
    PageImpl<ShareInfo> shareInfos = SHARE_INFO_SERVICE.findAllShareInfoList(pageable);
    return new ResponseEntity<>(shareInfos, HttpStatus.OK);
  }

  @Operation(
      summary = "find ShareInfo",
      description = "find ShareInfo",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "find Success",
            content = @Content(schema = @Schema(implementation = ShareInfoResponseDto.class))),
      })
  @GetMapping("/{id}")
  public ResponseEntity<ShareInfoResponseDto> findShareInfo(@PathVariable Long id) {
    ShareInfo findShareInfoResult = SHARE_INFO_SERVICE.findShareInfoById(id);
    return new ResponseEntity<>(
        SHARE_INFO_MAPPER.getDataFromEntity(findShareInfoResult), HttpStatus.OK);
  }
}
