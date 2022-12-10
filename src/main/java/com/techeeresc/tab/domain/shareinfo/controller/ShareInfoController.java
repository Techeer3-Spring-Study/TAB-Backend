package com.techeeresc.tab.domain.shareinfo.controller;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.service.ShareInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shareinfo")
public class ShareInfoController {
    private final ShareInfoService shareInfoService;
    private final ShareInfoMapper shareInfoMapper;
    @PostMapping
    public ShareInfoCreateRequestDto createPost(@RequestBody ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        ShareInfo insertSherInofoResult = shareInfoService.insertShareInfo(shareInfoCreateRequestDto);
        return shareInfoMapper.fromEntity(insertSherInofoResult);

    }
}