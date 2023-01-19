package com.techeeresc.tab.domain.bookmark.controller;

import com.techeeresc.tab.domain.bookmark.service.BookmarkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class) //-> 이 부분 JUnit5로 넘어오면서 RunWith->ExtendWith(SpringRunner)로 변환
@AutoConfigureMockMvc() //-> MockMvc 주입받는 2가지 방법 -> @SpringBootTest + @AutoConfigureMockMvc 애노테이션 붙이기
@SpringBootTest()
class BookmarkControllerTest {
    /**
     * 웹 API를 테스트할 때 사용
     * 스프링 MVC 테스트의 시작점
     * HTTP GET, POST 등에 대해 API 테스트 가능
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookmarkService bookmarkService;

    @Test
    void BookmarkControllerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bookmark"))
                .andExpect(MockMvcResultMatchers.status().isOk()) //Result 상태
                .andDo(MockMvcResultHandlers.print()); //응답 받은 내용을 Print처리하기//Get으로 받아온다.
    }
}