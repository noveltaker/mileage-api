package com.example.mileageapi.domain;

import com.example.mileageapi.constants.MileageHistoryType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "mileage_history",
    indexes = {@Index(name = "mileage_history_index_1", columnList = "user_id,review_id")})
public class MileageHistory extends AbstractCreatedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 리뷰의 아이디
  @Column(columnDefinition = "BINARY(16)", nullable = false, name = "review_id")
  private UUID reviewId;

  // 마일리지 내역 타입
  @Enumerated(EnumType.STRING)
  private MileageHistoryType type;

  // 장소 아이디
  @Column(columnDefinition = "BINARY(16)", nullable = false)
  private UUID placeId;

  // 변경된 포인트 값
  @Column(nullable = false)
  private Integer point;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private Mileage mileage;
}
