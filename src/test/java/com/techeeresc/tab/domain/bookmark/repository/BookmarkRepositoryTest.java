package com.techeeresc.tab.domain.bookmark.repository;

import com.techeeresc.tab.domain.bookmark.dto.mapper.BookmarkMapper;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkPagingDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.*;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookmarkRepositoryTest {
    @Autowired
    BookmarkRepository bookmarkRepository;

    @Test
    @DisplayName("멤버가 DB에 저장이 잘 되는지 확인")
    void saveBookMark() {

        bookmarkRepository.findAll();
    }

}

