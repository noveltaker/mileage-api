package com.example.mileageapi.repository;

import com.example.mileageapi.domain.MileageHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MileageHistoryRepository extends JpaRepository<MileageHistory, Long> {
  long countByPlaceId(UUID placeId);

  List<MileageHistory> findByReviewId(UUID reviewId);

  <T> Page<T> findByMileage_UserId(Pageable pageable, UUID userId, Class<T> type);
}
