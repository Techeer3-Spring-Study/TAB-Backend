package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PageRequest;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.entity.QPost;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
    PostCreateRequestDto postCreateRequestDto;

    @BeforeEach
    void set_up() {
        // given
        postCreateRequestDto = PostCreateRequestDto.builder()
                .memberId(1L)
                .title("게시물 테스트 코드 테스트")
                .image("www.image.com")
                .file("file.com")
                .category("개발")
                .content("재밌네요")
                .hashtags("#개발")
                .build();
    }

    @Test
    void mock_주입_테스트() {
        // 테스트는 한 가지만 하라고 되어있지만, 간편성을 위해 모든 객체를 한 테스트에서 테스트 해보았다.
        assertTrue(postRepository != null);
        assertTrue(jpaQueryFactory != null);
        assertTrue(postMapper != null);
        assertTrue(postService != null);
    }

    @Test
    @Transactional
    void 게시물_생성() throws Exception {
        // 실제 객체를 사용하는 경우는 가능하지만, mock을 통해 가짜 객체를 주입받는 경우 null 에러 발생
        Post post = postMapper.saveDataToEntity(postCreateRequestDto);

        // given
        // import static org.mockito.ArgumentMatchers.any;를 import 하지 않으면 오류 발생
        when(postRepository.save(any())).thenReturn(post); // Mock 객체 주입
        // when
        Post result = postService.insertPost(postCreateRequestDto);
        // then
        verify(postRepository).save(any());
        assertThat(result).isEqualTo(post);
    }

    @Test
    @Transactional
    void 모든_게시물_조회() throws Exception {
        // given
        QPost qPost = QPost.post;
        PageRequest pageRequest = new PageRequest();
        Pageable pageable = pageRequest.of();

        List<Post> posts = Arrays.asList(
                Post.builder()
                        .memberId(1L)
                        .title("게시물 테스트 코드 테스트")
                        .image("www.image.com")
                        .file("file.com")
                        .category("개발")
                        .content("재밌네요")
                        .hashtags("#개발")
                        .build()
        );

        when(jpaQueryFactory.selectFrom(qPost)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()).thenReturn(posts);

        // when
        List<Post> results = postService.findAllPostListWithQueryDsl(pageable);

        // then
        verify(jpaQueryFactory).selectFrom(qPost)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        assertThat(results).isEqualTo(posts);
    }
//
//    @Test
//    void 없는_게시물_조회_예외() throws Exception {
//        assertThat(postService.findPostByIdAndIncreaseViews(1L).getViews()).isEqualTo(1);
//    }
}