package com.techeeresc.tab.domain.bookmark.repository;

import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkQueryDslRepository {

  Page<Bookmark> findAllBookmark(Pageable pageable);
}
