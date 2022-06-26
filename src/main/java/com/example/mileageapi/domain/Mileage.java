package com.example.mileageapi.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class Mileage extends AbstractDateEntity {

  @Id
  @Column(unique = true, columnDefinition = "BINARY(16)" , nullable = false)
  private UUID userId;

  private Integer point;

  @Builder.Default
  @OneToMany(mappedBy = "mileage")
  private Set<MileageHistory> historySet = new HashSet<>();

  @Transient
  public void sumPoint(Integer point) {
    this.point += point;
  }

  @PrePersist
  void prePersist() {
    if (point == null) {
      this.point = 0;
    }
  }
}
