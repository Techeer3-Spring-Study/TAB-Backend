package com.techeeresc.tab.domain.shareinfo.service;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.repository.ShareInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor   // postRepository 의 생성자를 위해 선언
@Service
public class ShareInfoService {
    private final ShareInfoRepository REPOSITORY;
    private final ShareInfoMapper MAPPER;
    @Transactional
    public ShareInfo insertShareInfo(ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        return REPOSITORY.save(MAPPER.ToEntity(shareInfoCreateRequestDto));
    }
}
