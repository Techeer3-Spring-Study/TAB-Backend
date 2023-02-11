package com.techeeresc.tab.domain.post.service;

import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostService {
    public Post insertPost(PostCreateRequestDto postCreateRequestDto);
    public Post updatePost(PostUpdateRequestDto postUpdateRequestDto);
    public Post increaseLikeNumbers(Long id);
    public void deletePost(Long id);
    public Post findPostByIdAndIncreaseViews(Long id);
    public List<Post> findByTitleContainsWordWithQueryDsl(String word, Pageable pageable);
    public List<Post> findAllPostListWithQueryDsl(Pageable pageable);
}
