package com.techeeresc.tab.domain.bookmark.entity;

import com.techeeresc.tab.global.common.Timestamp;
import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "Bookmark")
public class Bookmark extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name = "member_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long memberId;
    @Column(name = "post_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long postId;

    @Builder
    public Bookmark(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
}