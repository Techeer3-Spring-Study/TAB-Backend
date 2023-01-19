package com.techeeresc.tab.domain.post.service;

import com.techeeresc.tab.domain.post.dto.request.PageRequest;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    @Transactional
    void 게시물_생성_Mockito() {
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

        Post result = postService.insertPost(postCreateRequestDto);

        System.out.println(result.getTitle());
        System.out.println(result.getId());
    }

    @Test
    @Transactional
    void 불러오기() {
        PageRequest pageRequest = new PageRequest();  // page 1, size 10
        Pageable pageable = pageRequest.of();

        List<Post> pages = postService.findAllPostListWithQueryDsl(pageable);

        for (Post post : pages) {
            System.out.println(post.getId());
        }
    }
}