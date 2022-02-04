package com.hket.reactivewebservice.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {
    
    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        
        return RouterFunctions
            .route(GET("/mono").and(accept(MediaType.APPLICATION_JSON)),greetingHandler::mono)
            .andRoute(GET("/flux").and(accept(MediaType.APPLICATION_JSON)),greetingHandler::flux)
            .andRoute(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
            
    }
    
}