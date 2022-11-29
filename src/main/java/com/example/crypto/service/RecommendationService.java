package com.example.crypto.service;

import kz.crypto.api.model.CalculationItem;
import kz.crypto.api.model.CalculationResult;
import kz.crypto.api.model.NormalizedRangeItem;

import java.time.LocalDate;
import java.util.List;

public interface RecommendationService {

    List<CalculationItem> calculateByCrypto(String cryptoName);

    List<CalculationResult> calculateByInterval(String cryptoName, LocalDate startDate, LocalDate endDate);

    List<CalculationResult> calculateByMonth(String month);

    NormalizedRangeItem getHighestNormalizedRange();

    List<NormalizedRangeItem> getNormalizedRangeList(LocalDate specificDay);
}
