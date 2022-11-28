package com.techeeresc.tab.domain.post.entity;

import com.techeeresc.tab.global.common.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_id", nullable = false)
    private Long memberId;       // 외래키, 향후 수정할 예정
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
    private String hashtags;    // 배열 형태로 변경
    @Column(name = "is_anonymous", nullable = false)
    private boolean isAnonymous;
    @Column(name = "like_number", nullable = false)
    private int likeNumber;
    @Column(name = "views", nullable = false)
    private int views;
}
