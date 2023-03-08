package com.okon.auth.controller;

import com.okon.api.dto.UserDTO;
import com.okon.auth.converter.UserConverter;
import com.okon.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id) {
        log.debug("getUserById");
        return userConverter.convertToDTO(userService.findById(id));
    }

    @GetMapping("username/{username}")
    public Optional<UserDTO> getUser(@PathVariable String username) {
        log.debug("getUserByUsername");
        return userConverter.convertToDTO(userService.findByUsername(username));
    }

    @GetMapping
    public List<UserDTO> getUsers(Authentication authentication) {
        log.debug("getUsers");
        return userConverter.convertToDTO(userService.findAll());
    }

}
