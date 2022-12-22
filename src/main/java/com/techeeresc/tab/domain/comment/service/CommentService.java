package com.techeeresc.tab.domain.comment.service;

import com.techeeresc.tab.domain.comment.dto.mapper.CommentMapper;
import com.techeeresc.tab.domain.comment.dto.request.CommentCreateRequestDto;
import com.techeeresc.tab.domain.comment.dto.request.CommentUpdateRequestDto;
import com.techeeresc.tab.domain.comment.entity.Comment;
import com.techeeresc.tab.domain.comment.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.techeeresc.tab.domain.comment.repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository COMMENT_REPOSITORY;
    private final CommentMapper COMMENT_MAPPER;

    @Transactional
    public Comment findCommentById(Long id) {
        try {
            Comment comment = isCommentExisted(id);
            return comment;
        } catch(NullPointerException exception) {
            throw new CommentNotFoundException("The comment is not found.");
        }
    }
    private Comment isCommentExisted(Long id) {
        Comment comment = COMMENT_REPOSITORY.findById(id).orElseThrow(() ->
                new CommentNotFoundException("The comment is not found."));

        return comment;
    }

    @Transactional
    public Comment insertComment(CommentCreateRequestDto commentCreateRequestDto) {
        return COMMENT_REPOSITORY.save(COMMENT_MAPPER.saveDataToEntity(commentCreateRequestDto));
    }

    @Transactional
    public Comment updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
        try {
            Comment comment = isCommentExisted(commentUpdateRequestDto.getId());
            return comment.updateComment(commentUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new CommentNotFoundException("The comment is not found.");
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
            throw new CommentNotFoundException("The comment is not found.");
        }

        return readAllComment();
    }



}
