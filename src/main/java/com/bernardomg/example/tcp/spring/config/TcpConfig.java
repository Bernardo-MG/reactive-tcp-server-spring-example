
package com.bernardomg.example.tcp.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.tcp.spring.server.handler.EchoResponseHandler;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.tcp.TcpServer;

@Configuration
@Slf4j
public class TcpConfig {

    public TcpConfig() {
        super();
    }

    @Bean("tcpServer")
    public TcpServer getTcpServer(@Value("${tcp.port}") final Integer port) {
        final TcpServer server;

        log.debug("Starting TCP server");

        server = TcpServer.create();

        server
            // Adds request handler
            .handle(new EchoResponseHandler())
            // Binds to port
            .port(port)
            .bindNow();

        log.debug("Started TCP server at port {}", port);

        return server;
    }

}
