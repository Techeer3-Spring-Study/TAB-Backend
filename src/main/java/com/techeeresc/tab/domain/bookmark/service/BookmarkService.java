package com.techeeresc.tab.domain.bookmark.service;

import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookmarkService {

    public Bookmark save(BookmarkCreateRequestDto bookmarkCreateRequestDto);

    public List<Bookmark> deleteBookmark(Long id);

    public Bookmark findBookmarkById(Long id);

    public PageImpl<Bookmark> findAllBookmark(Pageable pageable);


}