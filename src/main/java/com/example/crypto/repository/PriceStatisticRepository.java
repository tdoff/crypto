package com.example.crypto.repository;

import com.example.crypto.entity.PriceStatisticEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface PriceStatisticRepository extends CrudRepository<PriceStatisticEntity, Long> {

    List<PriceStatisticEntity> findByDeletedAtIsNullAndCryptoName(String cryptoName);
    
    List<PriceStatisticEntity> findByDeletedAtIsNullAndCryptoNameAndTimestampBetween(String cryptoName, ZonedDateTime startDateTime, ZonedDateTime endDateTime);

    List<PriceStatisticEntity> findByDeletedAtIsNullAndTimestampBetween(ZonedDateTime startDateTime, ZonedDateTime endDateTime);
}
