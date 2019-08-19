package se.emst.timereport.application;

import reactor.core.publisher.Mono;
import se.emst.timereport.domain.TimeEntry;

import java.util.ArrayList;
import java.util.List;

public class TimeEntryService {
    private final List<TimeEntry> entries = new ArrayList<>();

    public void addEntry(TimeEntry timeEntry) {
        entries.add(timeEntry);

    }

    public Mono<List<TimeEntry>> getAllEntries() {
        return Mono.just(entries);
    }
}
