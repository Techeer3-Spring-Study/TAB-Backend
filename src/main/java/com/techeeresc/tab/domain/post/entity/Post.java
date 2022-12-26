package com.techeeresc.tab.domain.post.entity;

import com.techeeresc.tab.domain.post.dto.request.PostUpdateRequestDto;
import com.techeeresc.tab.global.common.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;

@NoArgsConstructor   // @Entity 어노테이션을 사용할 경우 기본 생성자는 필수이다.
@AllArgsConstructor
@Getter
@Builder
// @Builder @NoArgsConstructor 를 함께 사용하려면 @AllArgsConstructor 나 모든 필드를 가지는 생성자를 직접 만들어줘야한다.
@Entity   // @Entity 어노테이션을 사용하면 이 클래스는 테이블과 매핑할 클래스라는 것을 명시해준다.
@Table(name = "post")
public class Post extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //MySQL의 자동 생성 방식
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name = "member_id", nullable = false)
    private Long memberId;       // TODO: 외래키, 향후 외래키 매핑 필요
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "file")
    private String file;    // TODO: 파일도 여러개 올리도록 해야할지?
    @Column(name = "image")
    private String image;
    @Column(name = "hashtags")
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.
    @Column(name = "is_anonymous", nullable = false)
    private boolean isAnonymous;
    @Column(name = "like_numbers", nullable = false)
    @ColumnDefault("0")
    private int likeNumbers;
    @Column(name = "views", nullable = false)
    @ColumnDefault("0")
    private int views;

    public Post updatePost(PostUpdateRequestDto postUpdateRequestDto) {
        this.category = postUpdateRequestDto.getCategory();
        this.title = postUpdateRequestDto.getTitle();
        this.content = postUpdateRequestDto.getContent();
        this.file = postUpdateRequestDto.getFile();
        this.image = postUpdateRequestDto.getImage();
        this.hashtags = postUpdateRequestDto.getHashtags();
        this.isAnonymous = postUpdateRequestDto.isAnonymous();

        return this;
    }

    public Post increaseLikeNumbers(int likeNumbers) {
        likeNumbers++;
        this.likeNumbers = likeNumbers;

        return this;
    }

    public Post increaseViews(int views) {
        views++;
        this.views = views;

        return this;
    }
}
