package com.techeeresc.tab.domain.post.repository;

import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepositoryWithQueryDsl {
    List<Post> searchResults();
}
