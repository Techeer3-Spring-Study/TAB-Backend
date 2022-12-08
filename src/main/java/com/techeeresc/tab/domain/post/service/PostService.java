package com.techeeresc.tab.domain.post.service;

import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.exception.NotFoundException;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository POST_REPOSITORY;
    private final PostMapper POST_MAPPER;

    @Transactional
    public Post insertPost(PostCreateRequestDto postCreateRequestDto) {
        return POST_REPOSITORY.save(POST_MAPPER.saveDataToEntity(postCreateRequestDto));
    }

    @Transactional
    public List<Post> readAllPost() {
        return POST_REPOSITORY.findAll();
    }

    @Transactional
    public Post updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        try {
            Post post = isPostExisted(postUpdateRequestDto.getId());
            return post.updatePost(postUpdateRequestDto);
        } catch(NotFoundException exception) {
            System.out.println("게시물이 존재하지 않습니다.");
        }

        return null;
    }

    @Transactional
    public Post increaseLikeNumbers(PostUpdateRequestDto postUpdateRequestDto) {
        try {
            Post post = isPostExisted(postUpdateRequestDto.getId());
            return post.increaseLikeNumbers(postUpdateRequestDto.getLikeNumbers());
        } catch(NotFoundException exception) {
            System.out.println("게시물이 존재하지 않습니다.");
        }

        return null;
    }

    @Transactional
    public Post increaseViews(PostUpdateRequestDto postUpdateRequestDto) {
        try {
            Post post = isPostExisted(postUpdateRequestDto.getId());
            return post.increaseViews(postUpdateRequestDto.getViews());
        } catch(NotFoundException exception) {
            System.out.println("게시물이 존재하지 않습니다.");
        }

        return null;
    }

    @Transactional
    public List<Post> deletePost(Long id) {
        POST_REPOSITORY.deleteById(id);

        return readAllPost();
    }

    private Post isPostExisted(Long id) {
        Post post = POST_REPOSITORY.findById(id).orElseThrow(() ->
                new NotFoundException("게시물이 존재하지 않습니다."));

        return post;
    }
}
