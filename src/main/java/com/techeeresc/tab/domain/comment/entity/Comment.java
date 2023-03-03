package com.techeeresc.tab.domain.comment.entity;

import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.post.entity.Post;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false,
          foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

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
