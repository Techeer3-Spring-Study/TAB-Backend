package com.techeeresc.tab.domain.bookmark.service;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.exception.BookmarkNotFoundException;
import com.techeeresc.tab.domain.bookmark.repository.BookmarkRepository;
import com.techeeresc.tab.global.exception.exceptionclass.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository REPOSITORY;
    public final BookmarkMapper MAPPER;



    @Transactional
    public Bookmark save(BookmarkCreateRequestDto bookmarkCreateRequestDto) {
        return REPOSITORY.save(MAPPER.saveDataToEntity(bookmarkCreateRequestDto));
    }

    @Transactional
    public List<Bookmark> findAllBookmark() {
        return REPOSITORY.findAll();
    }

    @Transactional
    public List<Bookmark> deleteBookmark(Long id) {
        try {
            Bookmark bookmark = isBookmarkExisted(id);
            REPOSITORY.deleteById(bookmark.getId());
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
        return findAllBookmark();
    }

    @Transactional
    public Bookmark findBookmarkById(Long id) {
        try {
            Bookmark bookmark = isBookmarkExisted(id);
            return bookmark;
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    private Bookmark isBookmarkExisted(Long id) {
        Bookmark bookmark = REPOSITORY.findById(id).orElseThrow(() ->
                new NullPointerException());

        return bookmark;
    }


}