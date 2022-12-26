package com.okon.core.controller;


import com.okon.api.dto.UserDTO;
import com.okon.core.converter.UserConverter;
import com.okon.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id) {
        return userConverter.convertToDTO(userService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_SUPER_ADMIN')")
    @GetMapping
    public List<UserDTO> getUsers(Authentication authentication) {
        log.info("getUsers");
        return userConverter.convertToDTO(userService.findAll());
    }

}
