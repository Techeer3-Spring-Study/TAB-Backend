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
    private final ShareInfoService SHAREINFO_SERVICE;
    private final ShareInfoMapper SHAREINFO_MAPPER;

    @PostMapping
    public ResponseEntity<ShareInfoResponseDto> createShareInfo(@RequestBody ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        ShareInfo insertShareInfoResult = SHAREINFO_SERVICE.insertShareInfo(shareInfoCreateRequestDto);
        return new ResponseEntity(SHAREINFO_MAPPER.getDataFromEntity(insertShareInfoResult), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShareInfo> findAllShareInfo() {
        return SHAREINFO_SERVICE.findAllShareInfo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareInfoResponseDto> findShareInfo(@PathVariable Long id) {
        ShareInfo findShareInfoResult = SHAREINFO_SERVICE.findShareInfoById(id);
        return new ResponseEntity<>(SHAREINFO_MAPPER.getDataFromEntity(findShareInfoResult), HttpStatus.OK);
    }

    @PutMapping
     public ResponseEntity<ShareInfoResponseDto> updateShareInfo(@RequestBody ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
        ShareInfo updateShareInfoResult = SHAREINFO_SERVICE.updateShareInfo(shareInfoUpdateRequestDto);
        return new ResponseEntity<>(SHAREINFO_MAPPER.getDataFromEntity(updateShareInfoResult), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ShareInfo> deleteShareInfo(@PathVariable Long id) {
        List<ShareInfo> shareInfos = SHAREINFO_SERVICE.deleteShareInfo(id);

        return shareInfos;
    }

}