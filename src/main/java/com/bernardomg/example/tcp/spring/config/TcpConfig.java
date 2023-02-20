
package com.bernardomg.example.tcp.spring.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.tcp.spring.property.TcpProperties;
import com.bernardomg.example.tcp.spring.server.ReactorNettyTcpServer;
import com.bernardomg.example.tcp.spring.server.Server;
import com.bernardomg.example.tcp.spring.server.handler.EchoResponseHandler;

@Configuration
@EnableConfigurationProperties(TcpProperties.class)
public class TcpConfig {

    public TcpConfig() {
        super();
    }

    @Bean(name = "tcpServer", initMethod = "start", destroyMethod = "close")
    public Server getTcpServer(final TcpProperties properties) {
        return new ReactorNettyTcpServer(properties.getPort(), new EchoResponseHandler());
    }

}
