package com.techeeresc.tab.domain.comment.dto.request;

import com.techeeresc.tab.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateRequestDto {
    private Long memberId;
    private Long postId;
//    private Post post;
    private Long commentId;
    private String content;
    private int layer;
    private boolean isAnonymous;
}