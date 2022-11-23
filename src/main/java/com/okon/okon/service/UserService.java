package com.okon.okon.service;

import com.okon.okon.model.User;

import java.util.Optional;

public interface UserService {
    User insert(User user);
    Optional<User> findById(Long id);
}
