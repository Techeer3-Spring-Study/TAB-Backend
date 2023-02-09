package com.techeeresc.tab.domain.comment.entity;

import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.global.common.Timestamp;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamp {
  @Id // 대표값
  @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
  @Column(nullable = false, columnDefinition = "INT UNSIGNED")
  private Long id;

  @Column(name = "post_id", nullable = false)
  private Long postId;

  @Column(name = "member_id", nullable = false)
  private Long memberId;

  @Column(name = "comment_id", nullable = false)
  private Long commentId;

  @Column(name = "content")
  private String content;

  @Column(name = "layer")
  private int layer;

  @Column(name = "is_anonymous", nullable = false)
  private boolean isAnonymous;

  public Comment updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
    this.content = commentUpdateRequestDto.getContent();
    this.isAnonymous = commentUpdateRequestDto.isAnonymous();
    return this;
  }
}
