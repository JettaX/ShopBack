package com.okon.auth.converter;

import com.okon.api.dto.UserDTO;
import com.okon.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final RoleConvertor roleConvertor;

    public Optional<UserDTO> convertToDTO(Optional<User> user) {
        return user.map(this::convertToDTOHelper);
    }

    public List<UserDTO> convertToDTO(List<User> users) {
        return users.stream().map(this::convertToDTOHelper).toList();
    }

    public UserDTO convertToDTOHelper(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .roles(roleConvertor.convertToDTO(user.getRoles()))
                .build();
    }
}
