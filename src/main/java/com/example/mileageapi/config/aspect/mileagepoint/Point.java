package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.domain.MileageHistory;

import java.util.List;

public interface Point {

  List<MileageHistory> getPoints();
}
