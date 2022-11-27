package com.example.crypto.service;

import com.example.crypto.entity.*;
import kz.crypto.api.model.*;

import java.util.*;

public interface CryptoService {

    List<Crypto> findAll();

    CryptoEntity findById(Long id);

    Crypto createNewCrypto(CreateCryptoRequest request);

    void delete(Long cryptoId);
}
