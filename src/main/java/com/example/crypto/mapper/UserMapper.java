package com.example.crypto.mapper;

import com.example.crypto.entity.UserEntity;
import kz.crypto.api.model.CreateUserRequest;
import kz.crypto.api.model.UpdateUserRequest;
import kz.crypto.api.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements EntityDtoMapper<UserEntity, User, CreateUserRequest> {

    private final ModelMapper modelMapper;

    @Override
    public User toDto(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }

    @Override
    public UserEntity toEntity(CreateUserRequest dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    public UserEntity toEntity(UpdateUserRequest request, UserEntity entity) {
        modelMapper.map(request, entity);
        return entity;
    }
}
