package com.techeeresc.tab.domain.post.service;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class PostServiceTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostMapper postMapper;

    @Test
    @Transactional
    void 게시물_생성() throws Exception {
        /* given */
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

        /* when */
        Post resultPost = postRepository.save(postMapper.saveDataToEntity(postCreateRequestDto));

        /* then */
        assertThat(resultPost.getId()).isEqualTo(2L);
    }
}