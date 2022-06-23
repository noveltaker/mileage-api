package com.example.mileageapi.domain;

import com.example.mileageapi.constants.MileageType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Builder
@AllArgsConstructor
@IdClass(value = MileageKey.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mileage {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID reviewId;

    // mileage type
    @Id
    @Enumerated(EnumType.STRING)
    private MileageType type;

    // 리뷰 작성 유저의 아이디
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    // 장소 id
    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    @Column(nullable = false)
    private Integer count;

    @Transient
    public void changedTypeAndCount(MileageType type, Integer count) {
        this.count = count;
        this.type = type;
    }
}
