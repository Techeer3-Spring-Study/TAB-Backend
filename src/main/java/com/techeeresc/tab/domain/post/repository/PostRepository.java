package com.techeeresc.tab.domain.post.repository;

import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 직접적으로 데이터베이스와 소통하는 Dao
}
