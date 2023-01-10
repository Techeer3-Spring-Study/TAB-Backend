package com.techeeresc.tab.domain.shareinfo.repository;

import com.techeeresc.tab.domain.shareinfo.entity.ShareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareInfoRepository extends JpaRepository<ShareInfo, Long> {
}
