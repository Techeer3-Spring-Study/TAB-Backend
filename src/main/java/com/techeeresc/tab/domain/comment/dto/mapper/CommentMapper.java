package com.techeeresc.tab.domain.comment.dto.mapper;

import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
  private final MemberRepository MEMBER_REPOSITORY;
  private final PostRepository POST_REPOSITORY;

  public Comment saveDataToEntity(CommentCreateRequestDto commentCreateRequestDto) {
    Member member = MEMBER_REPOSITORY.findById(commentCreateRequestDto.getMemberId()).orElseThrow(NullPointerException::new);
    Post post = POST_REPOSITORY.findById(commentCreateRequestDto.getPostId()).orElseThrow(NullPointerException::new);
    return Comment.builder()
        .member(member)
        .post(post)
        .commentId(commentCreateRequestDto.getCommentId())
        .content(commentCreateRequestDto.getContent())
        .layer(commentCreateRequestDto.getLayer())
        .isAnonymous(commentCreateRequestDto.isAnonymous())
        .build();
  }

  public CommentResponseDto getDataFromEntity(Comment comment) {
    return CommentResponseDto.builder()
        .id(comment.getId())
        .memberName(comment.getMember().getName())
        .postId(comment.getPost().getId())
        .commentId(comment.getCommentId())
        .content(comment.getContent())
        .layer(comment.getLayer())
        .isAnonymous(comment.isAnonymous())
        .build();
  }
}
