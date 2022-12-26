package com.techeeresc.tab.domain.post.repository;

import com.techeeresc.tab.domain.post.entity.Post;
import java.util.List;

public interface PostQueryDslRepository {
    List<Post> findByTitleContainsWordWithQueryDsl(String word);
}
