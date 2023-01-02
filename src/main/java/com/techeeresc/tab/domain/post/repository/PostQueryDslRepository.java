package com.techeeresc.tab.domain.post.repository;

import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryDslRepository {
    List<Post> findByTitleContainsWordWithQueryDsl(String word);
    Page<Post> findAllPostListWithQueryDsl(Pageable pageable);

}
