package se.emst.timereport.infrastructure.memory;

import reactor.core.publisher.Mono;
import se.emst.timereport.domain.EntryId;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final List<TimeEntry> entries = new ArrayList<>();
    private int index = 0;

    @Override
    public Mono<List<TimeEntry>> findAll() {
        return Mono.just(entries);
    }

    @Override
    public Mono<TimeEntry> addEntry(TimeEntry timeEntry) {
        index += 1;
        TimeEntry entry = new TimeEntry(timeEntry.getHours(), new EntryId(Integer.toString(index)));
        entries.add(entry);
        return Mono.just(entry);
    }

    @Override
    public Mono<TimeEntry> findOne(EntryId entryId) {
        return entries.stream().filter(timeEntry -> timeEntry.getId().equals(entryId)).findFirst().map(Mono::just).orElse(Mono.empty());
    }
}
