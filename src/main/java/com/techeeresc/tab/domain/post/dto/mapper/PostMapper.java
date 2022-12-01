package com.techeeresc.tab.domain.post.dto.mapper;

import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor   // postRepository 의 생성자를 위해 선언
public class PostMapper {
    public Post saveDataToEntity(PostCreateRequestDto postCreateRequestDto) {
        return Post.builder()
                .memberId(postCreateRequestDto.getMemberId())
                .category(postCreateRequestDto.getCategory())
                .title(postCreateRequestDto.getCategory())
                .content(postCreateRequestDto.getContent())
                .file(postCreateRequestDto.getFile())
                .image(postCreateRequestDto.getImage())
                .hashtags(postCreateRequestDto.getHashtags())
                .isAnonymous(postCreateRequestDto.getIsAnonymous())
                .likeNumbers(postCreateRequestDto.getLikeNumbers())
                .views(postCreateRequestDto.getViews())
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
                .isAnonymous(post.getIsAnonymous())   // 여기 왜 인식이 안되지..?
                .build();
    }
}
