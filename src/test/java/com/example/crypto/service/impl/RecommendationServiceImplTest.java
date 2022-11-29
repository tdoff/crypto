package com.example.crypto.service.impl;

import com.example.crypto.entity.CryptoEntity;
import com.example.crypto.entity.PriceStatisticEntity;
import com.example.crypto.mapper.CryptoMapper;
import com.example.crypto.service.PriceStatisticService;
import kz.crypto.api.model.CalculationItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {

    @Mock
    private PriceStatisticService priceStatisticService;

    @Mock
    private CryptoMapper cryptoMapper;

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Test
    void shouldReturnCorrectList_WhenCalculateByCrypto() {
        String cryptoName = "BTC";
        when(priceStatisticService.findByCryptoName(cryptoName)).thenReturn(getPriceStatistics());

        List<CalculationItem> calculationItems = recommendationService.calculateByCrypto(cryptoName);

        assertEquals(4, calculationItems.size());

    }

    private List<PriceStatisticEntity> getPriceStatistics() {
        CryptoEntity crypto = new CryptoEntity(1L, "BTC");
        return Arrays.asList(
                new PriceStatisticEntity(1L, LocalDate.of(2022, 2, 25).atStartOfDay(ZoneId.systemDefault()), crypto, BigDecimal.valueOf(458.56)),
                new PriceStatisticEntity(2L, LocalDate.of(2022, 1, 15).atStartOfDay(ZoneId.systemDefault()), crypto, BigDecimal.valueOf(1551.78)),//oldest
                new PriceStatisticEntity(3L, LocalDate.of(2022, 11, 8).atStartOfDay(ZoneId.systemDefault()), crypto, BigDecimal.valueOf(159.50)),//newest and min
                new PriceStatisticEntity(4L, LocalDate.of(2022, 7, 31).atStartOfDay(ZoneId.systemDefault()), crypto, BigDecimal.valueOf(5478.15))//max
        );
    }
}
