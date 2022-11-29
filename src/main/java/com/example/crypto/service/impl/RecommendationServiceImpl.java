package com.example.crypto.service.impl;

import com.example.crypto.entity.CryptoEntity;
import com.example.crypto.entity.PriceStatisticEntity;
import com.example.crypto.mapper.CryptoMapper;
import com.example.crypto.service.PriceStatisticService;
import com.example.crypto.service.RecommendationService;
import kz.crypto.api.model.CalculationItem;
import kz.crypto.api.model.CalculationResult;
import kz.crypto.api.model.NormalizedRangeItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.crypto.api.model.CalculationItem.CalculationTypeEnum.MAX;
import static kz.crypto.api.model.CalculationItem.CalculationTypeEnum.MIN;
import static kz.crypto.api.model.CalculationItem.CalculationTypeEnum.NEWEST;
import static kz.crypto.api.model.CalculationItem.CalculationTypeEnum.OLDEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final PriceStatisticService priceStatisticService;

    private final CryptoMapper cryptoMapper;

    @Override
    public List<CalculationItem> calculateByCrypto(String cryptoName) {
        var priceStatistics = priceStatisticService.findByCryptoName(cryptoName);
        return collectCalculationItems(priceStatistics);
    }

    @Override
    public List<CalculationResult> calculateByInterval(String cryptoName, LocalDate startDate, LocalDate endDate) {
        var priceStatistics = priceStatisticService.findByInterval(cryptoName, startDate, endDate);
        return getCalculationResults(priceStatistics);
    }

    @Override
    public List<CalculationResult> calculateByMonth(String month) {
        var priceStatistics = priceStatisticService.findByMonth(month);
        return getCalculationResults(priceStatistics);
    }

    @Override
    public NormalizedRangeItem getHighestNormalizedRange() {
        return null;
    }

    @Override
    public List<NormalizedRangeItem> getNormalizedRangeList(LocalDate specificDay) {
        return null;
    }

    private static List<CalculationItem> collectCalculationItems(List<PriceStatisticEntity> priceStatistics) {
        Optional<PriceStatisticEntity> min = priceStatistics.stream().min(Comparator.comparing(PriceStatisticEntity::getPrice));
        Optional<PriceStatisticEntity> max = priceStatistics.stream().max(Comparator.comparing(PriceStatisticEntity::getPrice));
        Optional<PriceStatisticEntity> oldest = priceStatistics.stream().min(Comparator.comparing(PriceStatisticEntity::getTimestampDate));
        Optional<PriceStatisticEntity> newest = priceStatistics.stream().max(Comparator.comparing(PriceStatisticEntity::getTimestampDate));

        List<CalculationItem> items = new ArrayList<>();

        processCalculation(min, items, MIN);

        processCalculation(max, items, MAX);

        processCalculation(oldest, items, OLDEST);

        processCalculation(newest, items, NEWEST);
        return items;
    }

    private static void processCalculation(Optional<PriceStatisticEntity> PriceStatisticOpt, List<CalculationItem> items, CalculationItem.CalculationTypeEnum type) {
        if (PriceStatisticOpt.isPresent()) {
            CalculationItem item = new CalculationItem();
            item.setCalculationType(type);
            item.setPrice(PriceStatisticOpt.get().getPrice());
            items.add(item);
        }
    }

    private List<CalculationResult> getCalculationResults(List<PriceStatisticEntity> priceStatistics) {
        Map<CryptoEntity, List<PriceStatisticEntity>> map = priceStatistics.stream().collect(Collectors.groupingBy(PriceStatisticEntity::getCrypto));

        return map.entrySet()
                .stream()
                .map(this::collectCalculationResult)
                .collect(Collectors.toList());
    }

    private CalculationResult collectCalculationResult(Map.Entry<CryptoEntity, List<PriceStatisticEntity>> e) {
        var calculationResult = new CalculationResult();
        calculationResult.setCrypto(cryptoMapper.toDto(e.getKey()));
        calculationResult.setItems(collectCalculationItems(e.getValue()));
        return calculationResult;
    }
}
