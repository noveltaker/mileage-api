package com.example.mileageapi.repository;

import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.domain.MileageHistoryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MileageHistoryRepository extends JpaRepository<MileageHistory, MileageHistoryKey> {
    long countByPlaceId(UUID placeId);

    List<MileageHistory> findByReviewId(UUID reviewId);
}
