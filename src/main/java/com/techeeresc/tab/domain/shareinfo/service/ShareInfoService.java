package com.techeeresc.tab.domain.shareinfo.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.shareinfo.dto.mapper.ShareInfoMapper;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.QShareInfo;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import com.techeeresc.tab.domain.shareinfo.repository.ShareInfoQueryDslRepository;
import com.techeeresc.tab.domain.shareinfo.repository.ShareInfoRepository;
import com.techeeresc.tab.global.exception.exceptionclass.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ShareInfoService implements ShareInfoQueryDslRepository {
    private final ShareInfoRepository REPOSITORY;
    private final ShareInfoMapper MAPPER;
    private final JPAQueryFactory JPA_QUERY_FACTORY;
    private final int NULL_SIZE = 0;

    @Transactional
    public ShareInfo insertShareInfo(ShareInfoCreateRequestDto shareInfoCreateRequestDto) {
        return REPOSITORY.save(MAPPER.saveDataToEntity(shareInfoCreateRequestDto));
    }

    @Transactional
    public ShareInfo updateShareInfo(ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
        try {
            ShareInfo shareInfo = isShareInfoExisted(shareInfoUpdateRequestDto.getId());
            return shareInfo.updateShareInfo(shareInfoUpdateRequestDto);
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<ShareInfo> findAllShareInfo() {
        return REPOSITORY.findAll();
    }

    @Transactional
    public List<ShareInfo> deleteShareInfo(Long id) {
        try {
            ShareInfo shareInfo = isShareInfoExisted(id);
            REPOSITORY.deleteById(shareInfo.getId());
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
        return findAllShareInfo();
    }

    @Transactional
    public ShareInfo findShareInfoById(Long id) {
        try {
            ShareInfo shareInfo = isShareInfoExisted(id);
            return shareInfo;
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public PageImpl<ShareInfo> findAllShareInfoList(Pageable pageable) {
        QShareInfo qShareInfo = QShareInfo.shareInfo;

        try {
            List<ShareInfo> shareInfos = JPA_QUERY_FACTORY.selectFrom(qShareInfo)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            isShareInfoExistedByList(shareInfos);
            return new PageImpl<>(shareInfos, pageable, shareInfos.size());
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    private ShareInfo isShareInfoExisted(Long id) {
        ShareInfo shareInfo = REPOSITORY.findById(id).orElseThrow(() ->
                new NullPointerException());

        return shareInfo;
    }

    private void isShareInfoExistedByList(List<ShareInfo> shareInfoSearchResults) {
        if (shareInfoSearchResults.size() == NULL_SIZE) {
            throw new NullPointerException();
        }
    }
}
