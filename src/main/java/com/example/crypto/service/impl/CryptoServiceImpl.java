package com.example.crypto.service.impl;

import com.example.crypto.entity.*;
import com.example.crypto.exception.*;
import com.example.crypto.mapper.*;
import com.example.crypto.repository.*;
import com.example.crypto.service.*;
import kz.crypto.api.model.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {

    private final CryptoRepository repository;

    private final CryptoMapper mapper;

    @Override
    public List<Crypto> findAll() {
        return repository.findByDeletedAtIsNull()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    @Override
    public CryptoEntity findById(Long id) {
        return getAccessibleCrypto(id);
    }

    @Override
    public Crypto createNewCrypto(CreateCryptoRequest request) {
        final var product = mapper.toEntity(request);
        return mapper.toDto(repository.save(product));
    }

    @Override
    public void delete(Long cryptoId) {
        final var crypto = getAccessibleCrypto(cryptoId);
        crypto.setDeletedAt(LocalDateTime.now());
        repository.save(crypto);
    }

    private CryptoEntity getAccessibleCrypto(Long cryptoId) {
        final var crypto = repository.findById(cryptoId)
                .orElseThrow(() -> new EntityNotFoundException(Crypto.class, cryptoId));

        if (crypto.getDeletedAt() != null) {
            throw new EntityDeletedException(Crypto.class, cryptoId, crypto.getDeletedAt());
        }

        return crypto;
    }
}
