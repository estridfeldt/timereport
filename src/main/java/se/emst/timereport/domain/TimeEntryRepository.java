package se.emst.timereport.domain;

import reactor.core.publisher.Mono;

import java.util.List;

public interface TimeEntryRepository {
    Mono<List<TimeEntry>> findAll();

    Mono<TimeEntry> addEntry(TimeEntry timeEntry);

    Mono<TimeEntry> findOne(EntryId entryId);
}
