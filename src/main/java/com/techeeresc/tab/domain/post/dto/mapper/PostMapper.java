package com.techeeresc.tab.domain.post.dto.mapper;

import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post saveDataToEntity(PostCreateRequestDto postCreateRequestDto) {
        return Post.builder()
                .memberId(postCreateRequestDto.getMemberId())
                .category(postCreateRequestDto.getCategory())
                .title(postCreateRequestDto.getTitle())
                .content(postCreateRequestDto.getContent())
                .file(postCreateRequestDto.getFile())
                .image(postCreateRequestDto.getImage())
                .hashtags(postCreateRequestDto.getHashtags())
                .isAnonymous(postCreateRequestDto.isAnonymous())
                .build();
    }

    public PostResponseDto getDataFromEntity(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .memberId(post.getMemberId())
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .file(post.getFile())
                .image(post.getImage())
                .hashtags(post.getHashtags())
                .likeNumbers(post.getLikeNumbers())
                .views(post.getViews())
                .isAnonymous(post.isAnonymous())   // boolean 타입의 변수인 경우 getXXX가 아니라 isXXX 이름으로 getter를 생성한다!
                .build();
    }
}
