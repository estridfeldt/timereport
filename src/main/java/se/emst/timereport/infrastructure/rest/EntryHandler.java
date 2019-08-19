package se.emst.timereport.infrastructure.rest;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import se.emst.timereport.domain.TimeEntry;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class EntryHandler {

    private final List<TimeEntry> entries = new ArrayList<>();

    Mono<ServerResponse> getEntries(ServerRequest request) {
        return ServerResponse.ok().body(BodyInserters.fromObject(entries));
    }

    Mono<ServerResponse> addEntry(ServerRequest request) {
        return request.bodyToMono(TimeEntry.class)
                .flatMap(timeEntry -> {
                    entries.add(timeEntry);
                    return ServerResponse.created(URI.create("")).build();
                });
    }
}
