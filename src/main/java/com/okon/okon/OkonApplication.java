package com.okon.okon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableTransactionManagement
@SpringBootApplication
public class OkonApplication {

    public static void main(String[] args) {
        SpringApplication.run(OkonApplication.class, args);
    }

}
