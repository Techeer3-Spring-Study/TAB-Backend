package com.techeeresc.tab.domain.post.service;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor   // postRepository 의 생성자를 위해 선언
@Service
public class PostService {
    private final PostRepository POST_REPOSITORY;
    private final PostMapper POST_MAPPER;

    @Transactional
    public Post insertPost(PostCreateRequestDto postCreateRequestDto) {
        Post post = POST_REPOSITORY.save(POST_MAPPER)
    }
}
