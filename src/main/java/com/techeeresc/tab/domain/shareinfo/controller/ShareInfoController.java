package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shareinfo")
public class ShareInfoController {
    private final ShareInfoService SHAREINFO_SERVICE;
    private final ShareInfoMapper SHAREINFO_MAPPER;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShareInfoCreateRequestDto createPost(@RequestBody ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        ShareInfo insertSherInfoResult = SHAREINFO_SERVICE.insertShareInfo(shareInfoCreateRequestDto);
        return SHAREINFO_MAPPER.getDataFromEntity(insertSherInfoResult);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShareInfo> readAllShareInfo() {
        return SHAREINFO_SERVICE.readAllShareInfo();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShareInfoCreateRequestDto findShareInfo(@PathVariable Long id) {
        ShareInfo shareInfo = SHAREINFO_SERVICE.findShareInfoById(id);
        return SHAREINFO_MAPPER.getDataFromEntity(shareInfo);
    }
    @PutMapping
    public ShareInfoCreateRequestDto updateShareInfo(@RequestBody ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
        ShareInfo updateShareInfoResult = SHAREINFO_SERVICE.updateShareInfo(shareInfoUpdateRequestDto);
        return SHAREINFO_MAPPER.getDataFromEntity(updateShareInfoResult);
    }
    @DeleteMapping("/{id}")//여기서부터 수정 진행 해주세요!
    @ResponseStatus(HttpStatus.OK)
    public List<ShareInfo> deleteShareInfo(@PathVariable Long id) {
        List<ShareInfo> shareInfos = SHAREINFO_SERVICE.deleteShareInfo(id);

        return shareInfos;
    }

}