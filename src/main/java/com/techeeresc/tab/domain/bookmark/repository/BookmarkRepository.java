package com.techeeresc.tab.domain.bookmark.repository;

import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


}
