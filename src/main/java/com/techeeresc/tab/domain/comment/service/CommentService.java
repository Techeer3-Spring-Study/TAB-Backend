package com.techeeresc.tab.domain.comment.service;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.repository.CommentRepository;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import com.techeeresc.tab.global.exception.customexception.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
  private final CommentRepository COMMENT_REPOSITORY;
  private final PostRepository POST_REPOSITORY;
  private final CommentMapper COMMENT_MAPPER;

  @Transactional
  public Comment createComment(Long postId, CommentCreateRequestDto commentCreateRequestDto) {
    try {
      Post post = isPostExistedById(postId);
      return COMMENT_REPOSITORY.save(COMMENT_MAPPER.saveDataToEntity(commentCreateRequestDto));
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  public Comment updateComment(Long postId, CommentUpdateRequestDto commentUpdateRequestDto) {
    try {
      Comment comment = isCommentExisted(commentUpdateRequestDto.getId());
      return comment.updateComment(commentUpdateRequestDto);
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  @Transactional
  public List<Comment> findAllComment() {
    return COMMENT_REPOSITORY.findAll();
  }

  @Transactional
  public void deleteComment(Long id) {
    try {
      Comment comment = isCommentExisted(id);
      COMMENT_REPOSITORY.deleteById(comment.getId());
    } catch (NullPointerException exception) {
      throw new RequestNotFoundException(
          StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
    }
  }

  private Comment isCommentExisted(Long id) {
    Comment comment = COMMENT_REPOSITORY.findById(id).orElseThrow(() -> new NullPointerException());
    return comment;
  }

  private Post isPostExistedById(Long id) {
    Post post = POST_REPOSITORY.findById(id).orElseThrow(() -> new NullPointerException());
    return post;
  }
}
