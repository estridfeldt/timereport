package se.emst.timereport.infrastructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import se.emst.timereport.domain.TimeEntry;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class Router {

    private final List<TimeEntry> entries = new ArrayList<>();

    @Bean
    RouterFunction routers() {
        return RouterFunctions.route(RequestPredicates.GET("/entries").and(accept(MediaType.APPLICATION_JSON_UTF8)), getEntries())
                .andRoute(RequestPredicates.POST("/entries").and(accept(MediaType.APPLICATION_JSON_UTF8)), addEntry());
    }

    private HandlerFunction<ServerResponse> getEntries() {
        return request -> ServerResponse.ok().body(BodyInserters.fromObject(entries));
    }

    private HandlerFunction<ServerResponse> addEntry() {
        return request -> request.bodyToMono(TimeEntry.class)
                .flatMap(timeEntry -> {
                    entries.add(timeEntry);
                    return ServerResponse.created(URI.create("")).build();
                });
    }
}
