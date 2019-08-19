package se.emst.timereport.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class Router {

    private EntryHandler entryHandler = new EntryHandler();

    @Bean
    RouterFunction routers() {
        return RouterFunctions.route(RequestPredicates.GET("/entries").and(accept(MediaType.APPLICATION_JSON_UTF8)), entryHandler::getEntries)
                .andRoute(RequestPredicates.POST("/entries").and(accept(MediaType.APPLICATION_JSON_UTF8)), entryHandler::addEntry);
    }

}
