package se.emst.timereport.domain;

import reactor.core.publisher.Mono;

import java.util.List;

public interface TimeEntryRepository {
    Mono<List<TimeEntry>> findAll();

    void addEntry(TimeEntry timeEntry);
}
