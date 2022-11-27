package com.example.crypto.repository;

import com.example.crypto.entity.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CryptoRepository extends CrudRepository<CryptoEntity, Long> {

    List<CryptoEntity> findByDeletedAtIsNull();
}
