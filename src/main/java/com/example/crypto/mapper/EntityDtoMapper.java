package com.example.crypto.mapper;

import java.util.*;

import static java.util.stream.Collectors.toUnmodifiableList;

public interface EntityDtoMapper<E, D, R> {

    D toDto(E entity);

    E toEntity(R createRequest);

    default List<D> toDto(final List<E> entities) {
        return entities.stream().map(this::toDto).collect(toUnmodifiableList());
    }
}
