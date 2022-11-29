package com.example.crypto.controller;

import com.example.crypto.service.*;
import kz.crypto.api.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

@RestController
@RequiredArgsConstructor
public class PriceStatisticController implements PriceStatisticsApi {

    private final PriceStatisticService service;

    @Override
    public ResponseEntity<Void> importStatistics(MultipartFile file) {
        service.processCsvFile(file);
        return ResponseEntity.ok().build();
    }
}
