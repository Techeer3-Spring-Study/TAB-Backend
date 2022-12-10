package com.techeeresc.tab.domain.bookmark.service;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository REPOSITORY;
    public final BookmarkMapper MAPPER;
    @Transactional
    public Bookmark save(BookmarkCreateRequestDto bookmarkCreateRequestDto) {
        return REPOSITORY.save(MAPPER.toEntity(bookmarkCreateRequestDto));

    }

}