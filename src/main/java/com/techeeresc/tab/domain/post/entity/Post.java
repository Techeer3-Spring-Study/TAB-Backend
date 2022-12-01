package com.techeeresc.tab.domain.post.entity;

import com.techeeresc.tab.global.common.Timestamp;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
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
    private String file;

    @Column(name = "image")
    private String image;

    @Column(name = "hashtags")
    private String hashtags;    // TODO: 한번에 여러개의 값을 받을 수 있도록 변경해야한다.

    @Column(name = "is_anonymous", nullable = false)
    private boolean isAnonymous;

    @Column(name = "like_number", nullable = false)
    private int likeNumber;

    @Column(name = "views", nullable = false)
    private int views;
}
