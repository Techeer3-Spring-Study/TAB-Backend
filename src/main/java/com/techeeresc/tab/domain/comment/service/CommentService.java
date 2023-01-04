package com.techeeresc.tab.domain.comment.service;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.exception.CommentNotFoundException;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import com.techeeresc.tab.global.exception.exceptionclass.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.techeeresc.tab.domain.comment.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository COMMENT_REPOSITORY;
    private final PostRepository POST_REPOSITORY;
    private final CommentMapper COMMENT_MAPPER;

    @Transactional
    public Comment insertComment(Long postId, CommentCreateRequestDto commentCreateRequestDto) {

        Post post =  POST_REPOSITORY.findById(postId).orElseThrow(() ->
                new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
                //new IllegalArgumentException("댓글 쓰기 실패! 해당 게시글이 존재하지 않음"));

        return COMMENT_REPOSITORY.save(COMMENT_MAPPER.saveDataToEntity(commentCreateRequestDto));
    }

    @Transactional
    public Comment updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
        try {
            Comment comment = isCommentExisted(commentUpdateRequestDto.getId());
            return comment.updateComment(commentUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<Comment> readAllComment() {
        return COMMENT_REPOSITORY.findAll();
    }

    @Transactional
    public List<Comment> deleteComment(Long id) {
        try{
            Comment comment = isCommentExisted(id);
            COMMENT_REPOSITORY.deleteById(comment.getId());
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }

        return readAllComment();
    }

    @Transactional
    public Comment findCommentById(Long id) {
        try {
            Comment comment = isCommentExisted(id);
            return comment;
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }
    private Comment isCommentExisted(Long id) {
        Comment comment = COMMENT_REPOSITORY.findById(id).orElseThrow(() ->
                new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);

        return comment;
    }


}
