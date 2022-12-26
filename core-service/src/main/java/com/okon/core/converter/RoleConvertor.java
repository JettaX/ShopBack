package com.okon.core.converter;

import com.okon.api.dto.RoleDTO;
import com.okon.core.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleConvertor {
    public RoleDTO convertToDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public List<RoleDTO> convertToDTO(List<Role> roles) {
        return roles.stream().map(this::convertToDTO).toList();
    }
}
