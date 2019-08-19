package se.emst.timereport.infrastructure.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import se.emst.timereport.application.AddTimeEntryRequest;
import se.emst.timereport.application.TimeEntryService;
import se.emst.timereport.domain.EntryId;
import se.emst.timereport.domain.TimeEntry;

import java.net.URI;
import java.util.HashMap;

class EntryHandler {

    private final TimeEntryService timeEntryService;

    EntryHandler(TimeEntryService timeEntryService) {
        this.timeEntryService = timeEntryService;
    }

    Mono<ServerResponse> getEntries(ServerRequest request) {
        return ServerResponse.ok().body(timeEntryService.getAllEntries(), new ParameterizedTypeReference<>() {
        });
    }

    Mono<ServerResponse> addEntry(ServerRequest request) {
        return request.bodyToMono(AddTimeEntryRequest.class)
                .flatMap(timeEntryService::addEntry)
                .flatMap(timeEntry -> {
                    URI location = UriComponentsBuilder.fromUri(request.uri()).pathSegment(timeEntry.getId().value()).build(new HashMap<>());
                    return ServerResponse.created(location).body(BodyInserters.fromObject(timeEntry));
                });
    }

    Mono<ServerResponse> findOneEntry(ServerRequest request) {
        return timeEntryService.findOneEntry(new EntryId(request.pathVariable("id")))
                .flatMap(timeEntry -> ServerResponse.ok().body(BodyInserters.fromObject(timeEntry)));
    }
}
