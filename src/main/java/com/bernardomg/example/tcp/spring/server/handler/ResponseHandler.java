
package com.bernardomg.example.tcp.spring.server.handler;

import java.util.function.BiFunction;

import org.reactivestreams.Publisher;

import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

public interface ResponseHandler extends BiFunction<NettyInbound, NettyOutbound, Publisher<Void>> {

}
