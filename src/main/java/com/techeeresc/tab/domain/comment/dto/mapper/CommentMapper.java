package com.techeeresc.tab.domain.comment.dto.mapper;

import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.response.CommentResponseDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment saveDataToEntity(CommentCreateRequestDto commentCreateRequestDto) {
        return Comment.builder()
                .memberId(commentCreateRequestDto.getMemberId())
                .postId(commentCreateRequestDto.getPostId())
//                .post(commentCreateRequestDto.getPost())
                .commentId(commentCreateRequestDto.getCommentId())
                .content(commentCreateRequestDto.getContent())
                .layer(commentCreateRequestDto.getLayer())
                .isAnonymous(commentCreateRequestDto.isAnonymous())
                .build();
    }

    public CommentResponseDto getDataFromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .memberId(comment.getMemberId())
                .postId(comment.getPostId())
//                .postId(comment.getPost().getId())
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .layer(comment.getLayer())
                .isAnonymous(comment.isAnonymous())
                .build();
    }

}
