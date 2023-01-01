package com.techeeresc.tab.domain.shareinfo.service;

import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.exception.ShareInfoNotFoundException;
import com.techeeresc.tab.domain.shareinfo.repository.ShareInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor   // postRepository 의 생성자를 위해 선언
@Service
public class ShareInfoService {
    private final ShareInfoRepository REPOSITORY;

    private final ShareInfoMapper MAPPER;

    @Transactional
    public ShareInfo findShareInfoById(Long id) {
        try {
            ShareInfo shareInfo = isShareInfoExisted(id);
            return shareInfo;
        } catch(NullPointerException exception) {
            throw new ShareInfoNotFoundException("The ShareInfo is not found.");
        }
    }

    private ShareInfo isShareInfoExisted(Long id) {
        ShareInfo shareInfo = REPOSITORY.findById(id).orElseThrow(() ->
                new ShareInfoNotFoundException("The ShareInfo is not found."));

        return shareInfo;
    }

    @Transactional
    public ShareInfo insertShareInfo(ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        return REPOSITORY.save(MAPPER.saveDataToEntity(shareInfoCreateRequestDto));
    }

    @Transactional
    public ShareInfo updateShareInfo(ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
        try {
            ShareInfo shareInfo = isShareInfoExisted(shareInfoUpdateRequestDto.getId());
            return shareInfo.updateShareInfo(shareInfoUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new ShareInfoNotFoundException("The ShareInfo is not found.");
        }
    }

    @Transactional
    public List<ShareInfo> findAllShareInfo() {
        return REPOSITORY.findAll();
    }

    @Transactional
    public List<ShareInfo> deleteShareInfo(Long id) {
        try{
            ShareInfo shareInfo = isShareInfoExisted(id);
            REPOSITORY.deleteById(shareInfo.getId());
        } catch(NullPointerException exception) {
            throw new ShareInfoNotFoundException("The comment is not found.");
        }
        return findAllShareInfo();
    }
}
