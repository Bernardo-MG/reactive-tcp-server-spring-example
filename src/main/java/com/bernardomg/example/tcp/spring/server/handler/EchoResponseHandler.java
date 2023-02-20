
package com.bernardomg.example.tcp.spring.server.handler;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import io.netty.util.CharsetUtil;
import reactor.core.publisher.Mono;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

@Component
public final class EchoResponseHandler implements ResponseHandler {

    @Override
    public final Publisher<Void> apply(final NettyInbound request, final NettyOutbound response) {
        // Receives the request and then sends a response
        return request.receive()
            // Handle request
            .doOnNext(next -> {
                final String message;

                // Sends the request to the listener
                message = next.toString(CharsetUtil.UTF_8);

                // Send response
                response.sendString(Mono.just(message))
                    .then()
                    .subscribe()
                    .dispose();
            })
            .then();
    }

}
