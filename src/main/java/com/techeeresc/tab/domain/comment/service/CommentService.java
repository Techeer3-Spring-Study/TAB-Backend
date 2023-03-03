package com.techeeresc.tab.domain.comment.service;

import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import java.util.List;

public interface CommentService {
  public Comment createComment(Long postId, CommentCreateRequestDto commentCreateRequestDto);

  public Comment updateComment(Long postId, CommentUpdateRequestDto commentUpdateRequestDto);

  public List<Comment> findAllComment();

  public void deleteComment(Long id);
}
