package com.okon.okon.converter;

import com.okon.okon.dto.UserDTO;
import com.okon.okon.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ModelConverter {

    public Optional<UserDTO> userConvertToDTO(Optional<User> user) {
        return user.map(this::userConvertToDTOHelper);
    }

    public List<UserDTO> usersConvertToDTO(List<User> users) {
        return users.stream().map(this::userConvertToDTOHelper).toList();
    }

    public UserDTO userConvertToDTOHelper(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .roles(user.getRoles())
                .build();
    }
}
