package com.example.crypto.mapper;

import com.example.crypto.entity.*;
import kz.crypto.api.model.*;
import lombok.*;
import org.modelmapper.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CryptoMapper implements EntityDtoMapper<CryptoEntity, Crypto, CreateCryptoRequest>{

    private final ModelMapper modelMapper;

    @Override
    public Crypto toDto(CryptoEntity entity) {
        return modelMapper.map(entity, Crypto.class);
    }

    @Override
    public CryptoEntity toEntity(CreateCryptoRequest request) {
        return modelMapper.map(request, CryptoEntity.class);
    }
}
