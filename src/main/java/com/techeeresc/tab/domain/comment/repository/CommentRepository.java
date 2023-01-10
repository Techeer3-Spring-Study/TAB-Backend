package com.techeeresc.tab.domain.comment.repository;

import com.techeeresc.tab.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}