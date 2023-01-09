package com.techeeresc.tab.domain.post.entity;

import com.techeeresc.tab.domain.post.controller.PostController;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import com.techeeresc.tab.domain.post.service.PostService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void insertPost() {
        PostCreateRequestDto postCreateRequestDto = PostCreateRequestDto.builder()
                .memberId(1L)
                .title("JunitTest")
                .image("www.image.com")
                .file("file.com")
                .category("개발")
                .content("재밌네요")
                .hashtags("#개발")
                .build();

        Post post = new Post(1L, postCreateRequestDto.getMemberId(), postCreateRequestDto.getCategory(), postCreateRequestDto.getTitle(),
                postCreateRequestDto.getContent(), postCreateRequestDto.getFile(), postCreateRequestDto.getImage(), postCreateRequestDto.getHashtags(),
                postCreateRequestDto.isAnonymous(), 0, 0);

        assertEquals(post.isAnonymous(),false);
    }

}