package com.example.crypto.service;

import kz.crypto.api.model.CreateUserRequest;
import kz.crypto.api.model.UpdateUserRequest;
import kz.crypto.api.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(CreateUserRequest body);

    List<User> findAll();

    void delete(Long userId);

    void update(Long id, UpdateUserRequest body);

    User findById(Long id);
}
