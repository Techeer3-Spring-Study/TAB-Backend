package com.techeeresc.tab.domain.comment.dto.request;

import com.techeeresc.tab.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.ManyToOne;

@Getter
@Builder
public class CommentUpdateRequestDto {
    private Long id;

    private Long memberId;

    private Long postId;

//    private Post post;

    private Long commentId;

    private String content;

    private int layer;

    private boolean isAnonymous;

}
