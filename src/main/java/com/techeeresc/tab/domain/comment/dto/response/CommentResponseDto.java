package com.techeeresc.tab.domain.comment.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
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

