package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.entity.QPost;
import com.techeeresc.tab.domain.post.repository.PostQueryDslRepository;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import com.techeeresc.tab.global.exception.customexception.RequestNotFoundException;
import com.techeeresc.tab.global.status.StatusCodes;
import com.techeeresc.tab.global.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService implements PostQueryDslRepository {
    private final PostRepository POST_REPOSITORY;
    private final PostMapper POST_MAPPER;
    private final JPAQueryFactory JPA_QUERY_FACTORY;
    private final int NULL_SIZE = 0;

    @Transactional
    public Post insertPost(PostCreateRequestDto postCreateRequestDto) {
        return POST_REPOSITORY.save(POST_MAPPER.saveDataToEntity(postCreateRequestDto));
    }

    @Transactional
    public Post updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        try {
            Post post = isPostExistedById(postUpdateRequestDto.getId());
            return post.updatePost(postUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public Post increaseLikeNumbers(Long id) {
        try {
            Post post = isPostExistedById(id);
            return post.increaseLikeNumbers(post.getLikeNumbers());
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public void deletePost(Long id) {
        try {
            Post post = isPostExistedById(id);
            POST_REPOSITORY.deleteById(post.getId());
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public Post findPostByIdAndIncreaseViews(Long id) {
        try {
            Post post = isPostExistedById(id);
            post = increaseViews(post);
            return post;
        } catch(NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public List<Post> findByTitleContainsWordWithQueryDsl(String word) {
        QPost qPost = QPost.post;

        try {
            List<Post> postSearchResults = JPA_QUERY_FACTORY.selectFrom(qPost)
                    .where(qPost.title.contains(word)).fetch();

            isPostExistedByList(postSearchResults);

            return postSearchResults;   // TODO: 페이징 결과를 리턴하도록 해야한다.
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    @Transactional
    public PageImpl<Post> findAllPostListWithQueryDsl(Pageable pageable) {
        QPost qPost = QPost.post;

        try {
            List<Post> posts = JPA_QUERY_FACTORY.selectFrom(qPost)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            isPostExistedByList(posts);

            return new PageImpl<>(posts, pageable, posts.size());
        } catch (NullPointerException exception) {
            throw new RequestNotFoundException(StatusMessage.NOT_FOUND.getStatusMessage(), StatusCodes.NOT_FOUND);
        }
    }

    private Post increaseViews(Post post) {
        return post.increaseViews(post.getViews());
    }


    private Post isPostExistedById(Long id) {
        Post post = POST_REPOSITORY.findById(id).orElseThrow(() ->
                new NullPointerException());

        return post;
    }

    private void isPostExistedByList(List<Post> postSearchResults) {
        if (postSearchResults.size() == NULL_SIZE) {
            throw new NullPointerException();
        }
    }
}