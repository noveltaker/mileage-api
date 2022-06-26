package com.example.mileageapi.repository;

import com.example.mileageapi.domain.Mileage;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, UUID> {

  <T> Optional<T> findByUserId(UUID id, Class<T> type);

  @Override
  Optional<Mileage> findById(UUID uuid);

  @EntityGraph(attributePaths = {"historySet"})
  Optional<Mileage> findByUserIdAndHistorySet_ReviewId(UUID userId, UUID reviewId);
}
