package com.techeeresc.tab.domain.bookmark.service;

import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.bookmark.repository.BookmarkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BookmarkServiceTest {
    @Autowired
    BookmarkRepository BOOKMARK_REPOSITORY;

    @Autowired
    BookmarkService BOOKMARK_SERVICE;


    @AfterEach
    void clean(){
        BOOKMARK_REPOSITORY.deleteAll();
    }

    @Test
    @DisplayName("/Bookmark 요청 시 DB에 값이 저장")
    void insertBookmark() {
        //given -> Controller Test와 마찬가지로 DTO인 BookmarkCreate 객체를 임의대로 Builder패턴을 이용하여 생성한다.
        BookmarkCreateRequestDto bookmarkCreateRequestDto = BookmarkCreateRequestDto.builder()
                .memberId(1L)
                .postId(2L)
                .build();

        //when
        BOOKMARK_SERVICE.save(bookmarkCreateRequestDto);

        //then
        assertEquals(1L, BOOKMARK_REPOSITORY.count());

        Bookmark bookmark = BOOKMARK_REPOSITORY.findAll().get(0);
        assertEquals(1L, bookmark.getMemberId());
        assertEquals(2L, bookmark.getPostId());

        System.out.println(bookmark);
    }

}