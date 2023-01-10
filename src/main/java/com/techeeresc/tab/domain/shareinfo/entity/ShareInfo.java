package com.techeeresc.tab.domain.shareinfo.entity;

import com.techeeresc.tab.domain.shareinfo.dto.request.ShareInfoUpdateRequestDto;
import com.techeeresc.tab.global.common.Timestamp;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "shareInfo")
public class ShareInfo extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "link")
    private String link;
    @Column(name = "image")
    private String image;
    @Column(name = "hashtag")
    private String hashtag;
    public ShareInfo updateShareInfo(ShareInfoUpdateRequestDto shareInfoUpdateRequestDto) {
        this.title = shareInfoUpdateRequestDto.getTitle();
        this.content = shareInfoUpdateRequestDto.getContent();
        this.link = shareInfoUpdateRequestDto.getLink();
        this.image = shareInfoUpdateRequestDto.getImage();
        this.hashtag = shareInfoUpdateRequestDto.getHashtag();

        return this;
    }
}


