package com.okon.auth.service;


import com.okon.auth.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User insert(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
}
