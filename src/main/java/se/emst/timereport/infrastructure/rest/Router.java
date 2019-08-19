package se.emst.timereport.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Collections;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class Router {

    @Bean
    RouterFunction routers() {
        return RouterFunctions.route(RequestPredicates.GET("/entries").and(accept(MediaType.APPLICATION_JSON_UTF8)), request -> ServerResponse.ok().body(BodyInserters.fromObject(Collections.emptyList())));
    }

}
