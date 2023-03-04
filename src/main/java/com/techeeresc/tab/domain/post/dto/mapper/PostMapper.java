package com.techeeresc.tab.domain.post.dto.mapper;

import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.domain.post.dto.request.PostCreateRequestDto;
import com.techeeresc.tab.domain.post.dto.response.PostResponseDto;
import com.techeeresc.tab.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
  private final MemberRepository MEMBER_REPOSITORY;
  public Post saveDataToEntity(PostCreateRequestDto postCreateRequestDto) {
    Member member = MEMBER_REPOSITORY.findById(postCreateRequestDto.getMemberId()).orElseThrow(NullPointerException::new);
    return Post.builder()
        .member(member)
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
        .memberName(post.getMember().getName())
        .category(post.getCategory())
        .title(post.getTitle())
        .content(post.getContent())
        .file(post.getFile())
        .image(post.getImage())
        .hashtags(post.getHashtags())
        .likeNumbers(post.getLikeNumbers())
        .views(post.getViews())
        .createdAt(post.getCreatedAt())
        .isAnonymous(post.isAnonymous()) // boolean 타입의 변수인 경우 getXXX가 아니라 isXXX 이름으로 getter를 생성한다!
        .build();
  }
}
