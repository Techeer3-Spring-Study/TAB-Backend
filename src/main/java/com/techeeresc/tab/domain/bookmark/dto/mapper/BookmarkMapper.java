package com.techeeresc.tab.domain.bookmark.dto.mapper;

import com.techeeresc.tab.domain.bookmark.dto.request.BookmarkCreateRequestDto;
import com.techeeresc.tab.domain.bookmark.dto.response.BookmarkResponseDto;
import com.techeeresc.tab.domain.bookmark.entity.Bookmark;
import com.techeeresc.tab.domain.member.entity.Member;
import com.techeeresc.tab.domain.member.respository.MemberRepository;
import com.techeeresc.tab.domain.post.entity.Post;
import com.techeeresc.tab.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {
  private final MemberRepository MEMBER_REPOSITORY;
  private final PostRepository POST_REPOSITORY;
  public Bookmark saveDataToEntity(BookmarkCreateRequestDto bookmarkCreateRequestDto) {
    Member member = MEMBER_REPOSITORY.findById(bookmarkCreateRequestDto.getMemberId()).orElseThrow(NullPointerException::new);
    Post post = POST_REPOSITORY.findById(bookmarkCreateRequestDto.getPostId()).orElseThrow(NullPointerException::new);
    return Bookmark.builder()
        .member(member)
        .post(post)
        .build();
  }

  public BookmarkResponseDto getDataFromEntity(Bookmark bookmark) {
    return BookmarkResponseDto.builder()
        .id(bookmark.getId())
        .memberName(bookmark.getMember().getName())
        .postId(bookmark.getPost().getId())
        .build();
  }
}
