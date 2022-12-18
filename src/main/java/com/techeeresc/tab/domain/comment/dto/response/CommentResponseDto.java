package com.techeeresc.tab.domain.comment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {
    private Long id;

    private Long memberId;

    private Long postId;

    private Long commentId;

    private String content;

    private int layer;

    private boolean isAnonymous;
}

