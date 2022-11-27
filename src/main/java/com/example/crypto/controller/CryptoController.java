package com.example.crypto.controller;

import com.example.crypto.mapper.*;
import com.example.crypto.service.*;
import kz.crypto.api.*;
import kz.crypto.api.model.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class CryptoController implements CryptosApi {


    private final CryptoService service;

    private final CryptoMapper mapper;

    @Override
    public ResponseEntity<Crypto> createCrypto(CreateCryptoRequest body) {
        return new ResponseEntity<>(service.createNewCrypto(body), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCrypto(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Crypto> getCrypto(Long id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @Override
    public ResponseEntity<List<Crypto>> getCryptos() {
        return ResponseEntity.ok(service.findAll());
    }
}
