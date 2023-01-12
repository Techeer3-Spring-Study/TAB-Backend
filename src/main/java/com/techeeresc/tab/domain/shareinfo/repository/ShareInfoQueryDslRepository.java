package com.techeeresc.tab.domain.shareinfo.repository;

import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShareInfoQueryDslRepository {
    Page<ShareInfo> findAllShareInfoList(Pageable pageable);

}
