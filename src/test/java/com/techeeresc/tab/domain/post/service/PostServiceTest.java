package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock   // 가짜 객체, 테스트 Run 시 실제 객체가 아닌 Mock 객체 반환
    private PostRepository postRepository;
    @Mock
    private JPAQueryFactory jpaQueryFactory;
    @Spy   // 실제 인스턴스를 사용한다.
    private PostMapper postMapper;
    @InjectMocks   // 테스트 대상 어노테이션, Mock 객체가 주입될 클래스
    private PostService postService;
    private PostCreateRequestDto postCreateRequestDto;
    private Post post;

    @BeforeEach
    void setUp() {
        postCreateRequestDto =
                PostCreateRequestDto.builder()
                        .memberId(1L)
                        .title("JunitTest")
                        .image("www.image.com")
                        .file("file.com")
                        .category("개발")
                        .content("재밌네요")
                        .hashtags("#개발")
                        .build();

        post = postMapper.saveDataToEntity(postCreateRequestDto);
    }

    @Test
    @DisplayName("게시물 생성")
    @Transactional
    void 게시물_생성() throws Exception {
        // given
        when(postRepository.save(post)).thenReturn(post);

        // when
        Post result = postService.insertPost(postCreateRequestDto);

        // then
        verify(postRepository).save(result);
    }

    @Test
    @Transactional
    void 페이징_결과_불러오기() {
        // given
        when(postRepository.findById(1L)).thenReturn()

        // when

        // then
    }
//
//    @Test
//    @Transactional
//    // @Rollback(false)
//    void 게시물_삭제() {
//
//    }
//
//    @Test
//    @Transactional
//    void 게시물_수정() {
//
//    }
//
//    @Test
//    @Transactional
//    void 좋아요_증가() {
//
//    }
//
//    @Test
//    @Transactional
//    void 제목으로_검색하기() {
//
//    }
//
//    @Test
//    @Transactional
//    void 게시물_없을때_예외처리() {
//
//    }
}