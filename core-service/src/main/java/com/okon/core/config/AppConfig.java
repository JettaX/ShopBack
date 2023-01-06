package com.okon.core.config;

import com.okon.core.properties.AuthServiceIntegrationProperties;
import com.okon.core.properties.CartServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
@EnableConfigurationProperties(
        {CartServiceIntegrationProperties.class, AuthServiceIntegrationProperties.class}
)
@RequiredArgsConstructor
public class AppConfig {

    private final AuthServiceIntegrationProperties authServiceIntegrationProperties;
    private final CartServiceIntegrationProperties cartServiceIntegrationProperties;

    @Bean
    public WebClient usersServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, authServiceIntegrationProperties.getConnectionTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(authServiceIntegrationProperties.getReadTimeout()));
                    connection.addHandlerLast(new WriteTimeoutHandler(authServiceIntegrationProperties.getWriteTimeout()));
                });
        return WebClient.builder()
                .baseUrl(authServiceIntegrationProperties.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    @Bean
    public WebClient cartServiceWebClient() {
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, cartServiceIntegrationProperties.getConnectionTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntegrationProperties.getReadTimeout()));
                    connection.addHandlerLast(new WriteTimeoutHandler(cartServiceIntegrationProperties.getWriteTimeout()));
                });
        return WebClient.builder()
                .baseUrl(cartServiceIntegrationProperties.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
