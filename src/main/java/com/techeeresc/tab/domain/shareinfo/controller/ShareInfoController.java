package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.response.ShareInfoResponseDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shareinfo")
public class ShareInfoController {
    private final ShareInfoService SHARE_INFO_SERVICE;
    private final ShareInfoMapper SHARE_INFO_MAPPER;

    @PostMapping
    public ResponseEntity<ShareInfoResponseDto> createShareInfo(@RequestBody ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        ShareInfo insertShareInfoResult = SHARE_INFO_SERVICE.insertShareInfo(shareInfoCreateRequestDto);
        return new ResponseEntity(SHARE_INFO_MAPPER.getDataFromEntity(insertShareInfoResult), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)     // TODO: 페이징 처리 필요
    public List<ShareInfo> findAllShareInfo() {
        return SHARE_INFO_SERVICE.findAllShareInfo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareInfoResponseDto> findShareInfo(@PathVariable Long id) {
        ShareInfo findShareInfoResult = SHARE_INFO_SERVICE.findShareInfoById(id);
        return new ResponseEntity<>(SHARE_INFO_MAPPER.getDataFromEntity(findShareInfoResult), HttpStatus.OK);
    }

    @PutMapping
     public ResponseEntity<ShareInfoResponseDto> updateShareInfo(@RequestBody ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
        ShareInfo updateShareInfoResult = SHARE_INFO_SERVICE.updateShareInfo(shareInfoUpdateRequestDto);
        return new ResponseEntity<>(SHARE_INFO_MAPPER.getDataFromEntity(updateShareInfoResult), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")   // TODO: 응답형태 list가 아닌 다른 형태로 변경하기
    @ResponseStatus(HttpStatus.OK)
    public List<ShareInfo> deleteShareInfo(@PathVariable Long id) {
        List<ShareInfo> shareInfos = SHARE_INFO_SERVICE.deleteShareInfo(id);

        return shareInfos;
    }

}