package com.example.crypto.controller;

import com.example.crypto.service.RecommendationService;
import kz.crypto.api.RecommendationsApi;
import kz.crypto.api.model.CalculationItem;
import kz.crypto.api.model.CalculationResult;
import kz.crypto.api.model.NormalizedRangeItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecommendationController implements RecommendationsApi {
    
    private final RecommendationService recommendationService;

    @Override
    public ResponseEntity<List<CalculationItem>> calculateByCrypto(String cryptoName) {
        return ResponseEntity.ok(recommendationService.calculateByCrypto(cryptoName));
    }

    @Override
    public ResponseEntity<List<CalculationResult>> calculateByInterval(String cryptoName, LocalDate startDate, LocalDate endDate) {
        return ResponseEntity.ok(recommendationService.calculateByInterval(cryptoName, startDate, endDate));
    }

    @Override
    public ResponseEntity<List<CalculationResult>> calculateByMonth(String month) {
        return ResponseEntity.ok(recommendationService.calculateByMonth(month));
    }

    @Override
    public ResponseEntity<NormalizedRangeItem> getHighestNormalizedRange(LocalDate specificDay) {
        return ResponseEntity.ok(recommendationService.getHighestNormalizedRange(specificDay));
    }

    @Override
    public ResponseEntity<List<NormalizedRangeItem>> getNormalizedRangeList() {
        return ResponseEntity.ok(recommendationService.getNormalizedRangeList());
    }
}
