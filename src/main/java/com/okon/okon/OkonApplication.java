package com.okon.okon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableWebSecurity
@EnableTransactionManagement
@SpringBootApplication
public class OkonApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkonApplication.class, args);
    }

}
