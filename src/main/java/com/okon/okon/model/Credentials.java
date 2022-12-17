package com.okon.okon.model;

import com.okon.okon.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    private UserDTO user;
    private String token;
}
