package com.okon.auth.config;


import com.okon.auth.model.Role;
import com.okon.auth.model.User;
import com.okon.auth.repository.RoleRepository;
import com.okon.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DBInitializer {
    @Autowired
    private void initUsers(UserService userService, RoleRepository roleRepository) {
        Role roleUser = Role.builder().name("USER").build();
        roleUser = roleRepository.save(roleUser);
        Role roleAdmin = Role.builder().name("ADMIN").build();
        roleAdmin = roleRepository.save(roleAdmin);
        Role roleSUPERAdmin = Role.builder().name("SUPER_ADMIN").build();
        roleSUPERAdmin = roleRepository.save(roleSUPERAdmin);

        User user0 = User.builder()
                .name("superadmin")
                .surname("superadmin")
                .username("superadmin")
                .password("$2a$12$s/m3PPvNG.6l.MmCf.TdR.z.xuf.bSQT0UhRLpfgtSOnPBWC0TlcC")
                .roles(List.of(roleSUPERAdmin))
                .build();

        User user1 = User.builder()
                .name("admin")
                .surname("admin")
                .username("admin")
                .password("$2a$12$s/m3PPvNG.6l.MmCf.TdR.z.xuf.bSQT0UhRLpfgtSOnPBWC0TlcC")
                .roles(List.of(roleAdmin))
                .build();

        User user2 = User.builder()
                .name("user")
                .surname("user")
                .username("user")
                .password("$2a$12$s/m3PPvNG.6l.MmCf.TdR.z.xuf.bSQT0UhRLpfgtSOnPBWC0TlcC")
                .roles(List.of(roleUser))
                .build();

        userService.insert(user0);
        userService.insert(user1);
        userService.insert(user2);
    }
}
