package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
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
import org.springframework.web.bind.annotation.PutMapping;
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
public class ShareInfoPutMethodController {

    private final ShareInfoService SHARE_INFO_SERVICE;
    private final ShareInfoMapper SHARE_INFO_MAPPER;

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
}

