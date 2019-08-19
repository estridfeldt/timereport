package se.emst.timereport.infrastructure.rest;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import se.emst.timereport.application.TimeEntryService;
import se.emst.timereport.domain.TimeEntry;

import java.net.URI;

class EntryHandler {

    private final TimeEntryService timeEntryService;

    EntryHandler(TimeEntryService timeEntryService) {
        this.timeEntryService = timeEntryService;
    }

    Mono<ServerResponse> getEntries(ServerRequest request) {
        return timeEntryService.getAllEntries().flatMap(timeEntries -> ServerResponse.ok().body(BodyInserters.fromObject(timeEntries)));
    }

    Mono<ServerResponse> addEntry(ServerRequest request) {
        return request.bodyToMono(TimeEntry.class)
                .flatMap(timeEntry -> {
                    timeEntryService.addEntry(timeEntry);
                    return ServerResponse.created(URI.create("")).build();
                });
    }
}
