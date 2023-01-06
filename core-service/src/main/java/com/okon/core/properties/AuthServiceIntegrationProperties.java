package com.okon.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Data
@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.auth-service.users")
public class AuthServiceIntegrationProperties {
    private String baseUrl;
    private Integer connectionTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
