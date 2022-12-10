package com.techeeresc.tab.domain.bookmark.entity;


import com.techeeresc.tab.global.common.Timestamped;
import lombok.*;
import com.techeeresc.tab.global.common.Timestamped;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Bookmark")
@Builder
@Entity
public class Bookmark extends Timestamped {
    @Id //PK를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MariaDB에서 사용하게된다
    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name = "member_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long memberId;
    @Column(name = "post_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long postId;
}