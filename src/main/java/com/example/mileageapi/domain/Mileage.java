package com.example.mileageapi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mileage extends AbstractDateEntity{

  @Id
  @Column(unique = true, columnDefinition = "BINARY(16)")
  private UUID userId;

  private Integer point;

  @Builder.Default
  @OneToMany(mappedBy = "mileage")
  private Set<MileageHistory> historySet = new HashSet<>();
}
