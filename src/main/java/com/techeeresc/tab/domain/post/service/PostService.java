package com.techeeresc.tab.domain.post.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techeeresc.tab.domain.post.dto.mapper.PostMapper;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.entity.QPost;
import com.techeeresc.tab.domain.post.exception.PostNotFoundException;
import com.techeeresc.tab.domain.post.repository.PostQueryDslRepository;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService implements PostQueryDslRepository {
    private final PostRepository POST_REPOSITORY;
    private final PostMapper POST_MAPPER;
    private final JPAQueryFactory JPA_QUERY_FACTORY;
    private final String ERROR_MESSAGE = "The Post is not found.";

    @Transactional
    public Post insertPost(PostCreateRequestDto postCreateRequestDto) {
        return POST_REPOSITORY.save(POST_MAPPER.saveDataToEntity(postCreateRequestDto));
    }

    @Transactional
    public List<Post> findAllPost() {
        return POST_REPOSITORY.findAll();
    }

    @Transactional
    public Post updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        try {
            Post post = isPostExisted(postUpdateRequestDto.getId());
            return post.updatePost(postUpdateRequestDto);
        } catch(NullPointerException exception) {
            throw new PostNotFoundException(ERROR_MESSAGE);
        }
    }

    @Transactional
    public Post increaseLikeNumbers(Long id) {
        try {
            Post post = isPostExisted(id);
            return post.increaseLikeNumbers(post.getLikeNumbers());
        } catch(NullPointerException exception) {
            throw new PostNotFoundException(ERROR_MESSAGE);
        }
    }

    @Transactional
    public List<Post> deletePost(Long id) {
        try {
            Post post = isPostExisted(id);
            POST_REPOSITORY.deleteById(post.getId());
        } catch (NullPointerException exception) {
            throw new PostNotFoundException(ERROR_MESSAGE);
        }

        return findAllPost();
    }

    @Transactional
    public Post findPostByIdAndIncreaseViews(Long id) {
        try {
            Post post = isPostExisted(id);
            post = increaseViews(post);
            return post;
        } catch(NullPointerException exception) {
            throw new PostNotFoundException(ERROR_MESSAGE);
        }
    }

    @Transactional
    public Post findPostById(Long id) {
        try {
            Post post = isPostExisted(id);
            return post;
        } catch(NullPointerException exception) {
            throw new PostNotFoundException(ERROR_MESSAGE);
        }
    }

    @Transactional
    public List<Post> findByTitleContainsWordWithQueryDsl(String word) {
        QPost qPost = QPost.post;

        try {
            List<Post> postSearchResults = JPA_QUERY_FACTORY.selectFrom(qPost)
                    .where(qPost.title.contains(word)).fetch();

            isPostResultsExisted(postSearchResults);

            return postSearchResults;
        } catch (NullPointerException exception) {
            throw new PostNotFoundException(ERROR_MESSAGE);
        }
    }

    private Post increaseViews(Post post) {
        return post.increaseViews(post.getViews());
    }

    private Post isPostExisted(Long id) {
        Post post = POST_REPOSITORY.findById(id).orElseThrow(() ->
                new NullPointerException());

        return post;
    }

    private void isPostResultsExisted(List<Post> postSearchResult) {
        if (postSearchResult.size() == 0) {
            throw new NullPointerException();
        }
    }
}
