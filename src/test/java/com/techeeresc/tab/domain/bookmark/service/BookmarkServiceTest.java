package com.techeeresc.tab.domain.bookmark.service;

import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// class BookmarkServiceTest {
//  @Autowired BookmarkRepository BOOKMARK_REPOSITORY;
//
//  @Autowired BookmarkService BOOKMARK_SERVICE;
//
//  @BeforeEach
//  void clean() {
//    BOOKMARK_REPOSITORY.deleteAll();
//  }
//
//  @Test
//  @DisplayName("Bookmark Create")
//  void insertBookmark() {
//    // given -> Controller Test와 마찬가지로 DTO인 BookmarkCreate 객체를 임의대로 Builder패턴을 이용하여 생성한다.
//    Bookmark bookmark = Bookmark.builder().memberId(1L).postId(2L).build();
//
//    // when -> When 단계에서는 BookmarkService의 Write()메소드를 사용하여 given단계에서 생성한 DTO 객체를 파라미터 값으로 넣어준다
//    BOOKMARK_REPOSITORY.save(bookmark);
//
//    // then
//    assertEquals(1, BOOKMARK_REPOSITORY.count());
//
//    Bookmark bookmarks = BOOKMARK_REPOSITORY.findAll().get(0);
//    assertEquals(1, bookmarks.getMemberId());
//    assertEquals(2, bookmarks.getPostId());
//
//    System.out.println(bookmarks);
//  }
//
//  @Test
//  @DisplayName("Bookmark Delete")
//  void DeleteBookmark() {
//    // given
//    Bookmark bookmark = Bookmark.builder().postId(1L).memberId(2L).build();
//    BOOKMARK_REPOSITORY.save(bookmark);
//
//    // when
//    BOOKMARK_SERVICE.deleteBookmark(bookmark.getId());
//
//    // then
//    Assertions.assertEquals(0, BOOKMARK_REPOSITORY.count());
//  }
//
//  @Test
//  @DisplayName("Bookmark Read")
//  void test3() {
//    // given
//    Bookmark bookmark = Bookmark.builder().memberId(1L).postId(2L).build();
//
//    BOOKMARK_REPOSITORY.save(bookmark);
//
//    // when
//    Bookmark response = BOOKMARK_SERVICE.findBookmarkById(bookmark.getId());
//
//    // then
//    assertNotNull(response);
//    assertEquals(2L, response.getPostId());
//    assertEquals(1L, response.getMemberId());
//  }
// }
