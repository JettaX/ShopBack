package com.okon.okon.repository;

import com.okon.okon.model.User;

import java.util.Optional;

public interface UserRepository {
    User insert(User user);
    Optional<User> findById(Long id);
}
