package com.example.mileageapi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID reviewId;

    private String content;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    @ElementCollection
    @CollectionTable(name = "attached_photo")
    private List<UUID> attachedPhotoIds;
}
