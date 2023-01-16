package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.entity.QPost;
import com.techeeresc.tab.domain.post.repository.PostQueryDslRepository;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

//@ExtendWith(MockitoExtension.class)   // 이거 넣으니까 되는 이유는..?
//@AutoConfigureMockMvc
@SpringBootTest
class PostServiceTest {
    //@Mock
    @Autowired
    private PostRepository postRepository;
   //@Mock
    @Autowired
    private PostMapper postMapper;
    //@Mock
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    //@Mock
    @Autowired
    private PostQueryDslRepository postQueryDslRepository;
    //@InjectMocks
    @Autowired
    private PostService postService;

    @Test
    @Transactional
    void 게시물_생성_Mockito() {
        // given
        PostCreateRequestDto postCreateRequestDto =
                PostCreateRequestDto.builder()
                        .memberId(1L)
                        .title("JunitTest")
                        .image("www.image.com")
                        .file("file.com")
                        .category("개발")
                        .content("재밌네요")
                        .hashtags("#개발")
                        .build();

        Post result = new Post();
//        given(postRepository.save(postMapper.saveDataToEntity(postCreateRequestDto)))
//                .willReturn(result);

        Post result2 = postService.insertPost(postCreateRequestDto);

        // System.out.println(result.getId());
        System.out.println(result2.getTitle());
    }
}