package com.techeeresc.tab.domain.comment.entity;

import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
//import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.post.entity.Post;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comment")
public class Comment {

    @Id //대표값
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 생성
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;

//    @ManyToOne //해당 Comment 엔티티 여러개가 하나의 post에 연결된다
//    @JoinColumn(name="post_id")
//    private Post post;
    @Column(name = "post_id", nullable = false)
    private Long postId;
//    private Post post;

    //@ManyToOne
//    @JoinColumn(name="member_id") !외래키 매핑 필요!
//    private Member member;
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
        this.postId = commentUpdateRequestDto.getPostId();
        this.commentId = commentUpdateRequestDto.getCommentId();
        this.content = commentUpdateRequestDto.getContent();
        this.layer = commentUpdateRequestDto.getLayer();
        this.isAnonymous = commentUpdateRequestDto.isAnonymous();
        return this;
    }


}

