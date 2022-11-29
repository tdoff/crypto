package com.example.crypto.controller;

import com.example.crypto.service.UserService;
import kz.crypto.api.UsersApi;
import kz.crypto.api.model.CreateUserRequest;
import kz.crypto.api.model.UpdateUserRequest;
import kz.crypto.api.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<User> createUser(@Valid CreateUserRequest body) {
        return new ResponseEntity<>(userService.save(body), CREATED);
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> updateUser(Long id, UpdateUserRequest body) {
        userService.update(id, body);
        return noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.delete(userId);
        return noContent().build();
    }

    @Override
    public ResponseEntity<User> getUser(Long id) {
        return ok(userService.findById(id));
    }
}
