package com.techeeresc.tab.domain.shareinfo.service;

import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoCreateRequestDto;
import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShareInfoService {
  public ShareInfo insertShareInfo(ShareInfoCreateRequestDto shareInfoCreateRequestDto);

  public ShareInfo updateShareInfo(ShareInfoUpdateRequestDto shareInfoUpdateRequestDto);

  public List<ShareInfo> deleteShareInfo(Long id);

  public ShareInfo findShareInfoById(Long id);

  public PageImpl<ShareInfo> findAllShareInfoList(Pageable pageable);
}
