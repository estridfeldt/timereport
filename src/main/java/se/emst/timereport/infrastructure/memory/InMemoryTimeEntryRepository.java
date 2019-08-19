package se.emst.timereport.infrastructure.memory;

import reactor.core.publisher.Mono;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final List<TimeEntry> entries = new ArrayList<>();

    @Override
    public Mono<List<TimeEntry>> findAll() {
        return Mono.just(entries);
    }

    @Override
    public void addEntry(TimeEntry timeEntry) {
        entries.add(timeEntry);
    }
}
