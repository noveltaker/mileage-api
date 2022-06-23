package com.example.mileageapi.repository;

import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, MileageKey> {
    long countByPlaceId(UUID placeId);

    List<Mileage> findByReviewId(UUID reviewId);
}
