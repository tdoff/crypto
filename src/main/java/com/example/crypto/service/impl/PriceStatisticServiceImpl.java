package com.example.crypto.service.impl;

import com.example.crypto.entity.CryptoEntity;
import com.example.crypto.entity.PriceStatisticEntity;
import com.example.crypto.repository.PriceStatisticRepository;
import com.example.crypto.service.*;
import com.example.crypto.util.DateUtils;
import lombok.*;
import lombok.extern.slf4j.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceStatisticServiceImpl implements PriceStatisticService {

    private final CryptoService cryptoService;

    private final PriceStatisticRepository repository;

    @Override
    public void processCsvFile(MultipartFile file) {
        try {
            BufferedReader fileReader = new BufferedReader(new
                    InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                log.debug("Processing record {}", csvRecord);
                createPriceStatistic(csvRecord);
            }
        } catch (Exception e) {
            log.error("Error while processing", e);
        }
    }

    private void createPriceStatistic(CSVRecord csvRecord) {
        ZonedDateTime timestamp = DateUtils.toZonedDateTime(Long.parseLong(csvRecord.get(0)));
        CryptoEntity crypto = cryptoService.findByName(csvRecord.get(1));
        BigDecimal price = new BigDecimal(csvRecord.get(2));

        var priceStatistic = new PriceStatisticEntity();
        priceStatistic.setTimestampDate(timestamp);
        priceStatistic.setCrypto(crypto);
        priceStatistic.setPrice(price);

        repository.save(priceStatistic);
    }

    @Override
    public List<PriceStatisticEntity> findByCryptoName(String cryptoName) {
        return repository.findByDeletedAtIsNullAndCryptoName(cryptoName);
    }

    @Override
    public List<PriceStatisticEntity> findByInterval(String cryptoName, LocalDate startDate, LocalDate endDate) {
        ZonedDateTime startDateTime = startDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = endDate.plusDays(1).atStartOfDay(ZoneId.systemDefault());

        return repository.findByDeletedAtIsNullAndCryptoNameAndTimestampDateBetween(cryptoName, startDateTime, endDateTime);
    }

    @Override
    public List<PriceStatisticEntity> findByMonth(String monthFormat) {
        String[] dateParts = monthFormat.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);

        ZonedDateTime startDateTime = startDate.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = endDate.atStartOfDay(ZoneId.systemDefault());

        return repository.findByDeletedAtIsNullAndTimestampDateBetween(startDateTime, endDateTime);
    }

    @Override
    public List<PriceStatisticEntity> findBySpecificDay(LocalDate specificDay) {
        ZonedDateTime startDateTime = specificDay.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endDateTime = startDateTime.plusDays(1);
        return repository.findByDeletedAtIsNullAndTimestampDateBetween(startDateTime, endDateTime);
    }

    @Override
    public List<PriceStatisticEntity> findAll() {
        return repository.findAllByDeletedAtIsNull();
    }
}
