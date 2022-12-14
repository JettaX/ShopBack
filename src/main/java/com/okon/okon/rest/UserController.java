package com.okon.okon.rest;

import com.okon.okon.converter.ModelConverter;
import com.okon.okon.dto.UserDTO;
import com.okon.okon.service.UserService;
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
    private final ModelConverter modelConverter;

    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable Long id) {
        return modelConverter.userConvertToDTO(userService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_SUPER_ADMIN')")
    @GetMapping
    public List<UserDTO> getUsers(Authentication authentication) {
        log.info("getUsers");
        return modelConverter.usersConvertToDTO(userService.findAll());
    }

}
