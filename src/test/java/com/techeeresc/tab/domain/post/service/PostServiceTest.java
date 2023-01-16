package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PageRequest;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostQueryDslRepository;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

//@ExtendWith(MockitoExtension.class)   // 이거 넣으니까 되는 이유는..?
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
    @Rollback(false)
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
        System.out.println(result2.getId());
    }

    @Test
    @Transactional
    void 불러오기() {
        // given
        PageRequest pageRequest = new PageRequest();  // page 1, size 10
        Pageable pageable = pageRequest.of();

        List<Post> pages = postService.findAllPostListWithQueryDsl(pageable);

        for (Post post : pages) {
            System.out.println(post.getId());
        }
    }
}