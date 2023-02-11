package com.techeeresc.tab.global.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.time.LocalDateTime;

@MappedSuperclass // JPA Entity 클래스들이 해당 클래스를 상속할 경우, 데이터 트래킹 필드로 인식
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 표현
@Getter
public class Timestamp {
  @CreatedDate // Entity 생성 후 저장될 때의 시간이 자동 저장
  private LocalDateTime createdAt;

  @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
  private LocalDateTime updatedAt;
}
