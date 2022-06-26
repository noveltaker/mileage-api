package com.example.mileageapi.repository;

import com.example.mileageapi.domain.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, UUID> {}
