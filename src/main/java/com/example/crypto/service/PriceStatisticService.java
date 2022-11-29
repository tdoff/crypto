package com.example.crypto.service;

import com.example.crypto.entity.PriceStatisticEntity;
import org.springframework.web.multipart.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface PriceStatisticService {

    void processCsvFile(MultipartFile file);

    List<PriceStatisticEntity> findByCryptoName(String cryptoName);

    List<PriceStatisticEntity> findByInterval(String cryptoName, LocalDate startDate, LocalDate endDate);

    List<PriceStatisticEntity> findByMonth(String monthFormat);


    List<PriceStatisticEntity> findBySpecificDay(LocalDate specificDay);

    List<PriceStatisticEntity> findAll();

}
