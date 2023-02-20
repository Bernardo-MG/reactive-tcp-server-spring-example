
package com.bernardomg.example.tcp.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.tcp.spring.server.ReactorNettyTcpServer;
import com.bernardomg.example.tcp.spring.server.Server;
import com.bernardomg.example.tcp.spring.server.handler.EchoResponseHandler;

@Configuration
public class TcpConfig {

    public TcpConfig() {
        super();
    }

    @Bean(name = "tcpServer", initMethod = "start", destroyMethod = "close")
    public Server getTcpServer(@Value("${tcp.port}") final Integer port) {
        return new ReactorNettyTcpServer(port, new EchoResponseHandler());
    }

}
