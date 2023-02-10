package com.techeeresc.tab.domain.post.repository;

import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryDslRepository {
  List<Post> findByTitleContainsWordWithQueryDsl(String word, Pageable pageable);

  List<Post> findAllPostListWithQueryDsl(Pageable pageable);
}
