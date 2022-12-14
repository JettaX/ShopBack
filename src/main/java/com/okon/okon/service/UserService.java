package com.okon.okon.service;

import com.okon.okon.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User insert(User user);

    List<User> findAll();
    Optional<User> findById(Long id);
}
