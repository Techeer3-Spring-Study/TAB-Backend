package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoPagingDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.response.ShareInfoResponseDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import com.techeeresc.tab.global.exception.response.ErrorResponse;
import com.techeeresc.tab.global.status.StatusMessage;
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
import org.springframework.web.bind.annotation.*;

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
public class ShareInfoController {
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
  @ResponseStatus(HttpStatus.OK)
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

  @Operation(
      summary = "updateShareInfo",
      description = "Update ShareInfo",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Update Success",
            content = @Content(schema = @Schema(implementation = ShareInfoResponseDto.class))),
      })
  @PutMapping
  public ResponseEntity<ShareInfoResponseDto> updateShareInfo(
      @RequestBody ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
    ShareInfo updateShareInfoResult = SHARE_INFO_SERVICE.updateShareInfo(shareInfoUpdateRequestDto);
    return new ResponseEntity<>(
        SHARE_INFO_MAPPER.getDataFromEntity(updateShareInfoResult), HttpStatus.CREATED);
  }

  @Operation(
      summary = "DeleteShareInfo",
      description = "ShareInfo Delete ",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Delete Success",
            content = @Content(schema = @Schema(implementation = ShareInfoResponseDto.class))),
      })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> ShareInfoDelete(@PathVariable Long id) {
    SHARE_INFO_SERVICE.deleteShareInfo(id);
    return new ResponseEntity<>(StatusMessage.OK.getStatusMessage(), HttpStatus.OK);
  }
}
