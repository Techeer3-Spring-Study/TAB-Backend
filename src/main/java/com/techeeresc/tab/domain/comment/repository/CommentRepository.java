package com.techeeresc.tab.domain.comment.repository;

import com.techeeresc.tab.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    // 특정 게시글의 모든 댓글 조회 : postID를 입력했을 때 comment묶음을 반환
//    @Query(value = "SELECT * FROM COMMENT WHERE post_id = :postID", nativeQuery = true);
}