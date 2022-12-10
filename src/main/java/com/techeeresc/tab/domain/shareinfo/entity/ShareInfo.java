package com.techeeresc.tab.domain.shareinfo.entity;

import com.techeeresc.tab.global.common.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shareInfo")
@Builder
@Entity
public class ShareInfo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "link", columnDefinition = "INT UNSIGNED")
    private Long link;
    @Column(name = "image")
    private String image;
    @Column(name = "hashtag")
    private String hashtag;
}
