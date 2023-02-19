package com.techeeresc.tab.domain.bookmark.controller;

import static org.junit.jupiter.api.Assertions.*;

// @ExtendWith(
//    SpringExtension
//        .class) // RunWith는 JUnit에 내장된 실행자 외 다른 실행자를 실행 -> 여기서 SpringRunner 실행자 즉 스프링 부트 테스트와
// JUnit
//// 사이에 연결자이다.
// @AutoConfigureMockMvc // -> MockMvc 주입받는 2가지 방법 -> @SpringBootTest + @AutoConfigureMockMvc 애노테이션
// 붙이기
// @SpringBootTest
// class BookmarkControllerTest {
//  /** 웹 API를 테스트할 때 사용 스프링 MVC 테스트의 시작점 HTTP GET, POST 등에 대해 API 테스트 가능 */
//  @Autowired private MockMvc mockMvc;
//
//  @MockBean BookmarkService bookmarkService;
//
//  @Autowired BookmarkRepository BOOKMARK_REPOSITORY;
//
//  @Autowired ObjectMapper objectMapper;
//
//  @Test
//  @DisplayName("상태확인")
//  void BookmarkControllerTest() throws Exception {
//
//    mockMvc
//        .perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/bookmark")) // Get으로
// 받아온다.
//        .andExpect(status().isOk()) // Result 상태
//        .andDo(print()); // 응답 받은 내용을 Print처리하기
//  }
//
//  @Test
//  @DisplayName("Bookmark GET")
//  void BookmarkPostTest() throws Exception {
//    mockMvc
//        .perform(
//            MockMvcRequestBuilders.get("http://localhost:8080/api/v1/bookmark")
//                .queryParam("id", "1")
//                .queryParam("postId", "1")
//                .queryParam("memberId", "2"))
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andDo(MockMvcResultHandlers.print());
//  }
//
//  @Test
//  @DisplayName("Bookmark POST")
//  void BookmarkRead() throws Exception {
//
//    // given
//    Bookmark bookmark = Bookmark.builder().memberId(2L).postId(3L).build();
//
//    BOOKMARK_REPOSITORY.save(bookmark);
//
//    String json = new ObjectMapper().writeValueAsString(bookmark); // Bookmark json Type으로 변형
//
//    // expected
//    mockMvc
//        .perform(
//            MockMvcRequestBuilders.get("http://localhost:8080/api/v1/bookmark")
//                .contentType(APPLICATION_JSON)
//                .content(json)) // Get으로 받아온다.
//        .andExpect(status().isOk()) // Result 상태
//        .andDo(print()); // 응답 받은 내용을 Print처리하기
//  }
// }
