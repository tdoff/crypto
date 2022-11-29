package com.example.crypto.service.impl;

import com.example.crypto.entity.CryptoEntity;
import com.example.crypto.exception.CryptoNotSupportedException;
import com.example.crypto.exception.EntityDeletedException;
import com.example.crypto.exception.EntityNotFoundException;
import com.example.crypto.repository.CryptoRepository;
import com.example.crypto.service.CryptoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CryptoServiceImplTest {

    @Mock
    private CryptoRepository repository;

    @InjectMocks
    private CryptoServiceImpl cryptoService;

    @Test
    @DisplayName("when crypto exists then return crypto")
    void findByName() {
        String cryptoName = "BTC";
        when(repository.findByName(cryptoName)).thenReturn(Optional.of(new CryptoEntity()));

        final var crypto = repository.findByName(cryptoName);

        assertThat(crypto).isNotNull();
    }

    @Test
    void shouldThrowException_WhenCryptoNotSupported() {
        assertThrows(CryptoNotSupportedException.class, () -> cryptoService.findByName("XYZ"));
    }

    @Test
    void shouldThrowException_WheCryptoIsDeleted() {
        final var entity = new CryptoEntity();
        String cryptoName = "XYZ";

        entity.setDeletedAt(LocalDateTime.now());
        when(repository.findByName(cryptoName))
                .thenReturn(Optional.of(entity));
        assertThrows(EntityDeletedException.class, () -> cryptoService.findByName(cryptoName));
    }
}
