
package com.bernardomg.example.tcp.server.config;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;
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
            .handle(this::handleRequest)
            // Binds to port
            .port(port)
            .bindNow();
        
        log.debug("Started TCP server at port {}", port);
        
        return server;
    }

    /**
     * Request event internal listener. Will receive any request sent by the client.
     * <p>
     * Will send the context info to the listener and send a response to the client.
     *
     * @param request
     *            request flux
     * @param response
     *            response flux
     * @return a publisher which handles the request
     */
    private final Publisher<Void> handleRequest(final NettyInbound request, final NettyOutbound response) {
        // Sends the response
        return response.sendString(Mono.just("abc"));
    }

}
