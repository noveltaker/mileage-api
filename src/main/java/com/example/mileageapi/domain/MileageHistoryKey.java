package com.example.mileageapi.domain;

import com.example.mileageapi.constants.MileageType;

import java.io.Serializable;
import java.util.UUID;

public class MileageHistoryKey implements Serializable {

    private UUID reviewId;

    private MileageType type;
}
