package se.emst.timereport.infrastructure.persistence;

import reactor.core.publisher.Mono;
import se.emst.timereport.domain.EntryId;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MongoTimeEntryRepository implements TimeEntryRepository {
    private final TimeEntryCrudRepository timeEntryCrudRepository;

    public MongoTimeEntryRepository(TimeEntryCrudRepository timeEntryCrudRepository) {

        this.timeEntryCrudRepository = timeEntryCrudRepository;
    }

    @Override
    public Mono<List<TimeEntry>> findAll() {
        return timeEntryCrudRepository.findAll().map(toTimeEntry()).collect(Collectors.toList());
    }

    @Override
    public Mono<TimeEntry> addEntry(TimeEntry timeEntry) {
        return timeEntryCrudRepository.save(new TimeDoc(timeEntry.getHours())).map(toTimeEntry());
    }

    @Override
    public Mono<TimeEntry> findOne(EntryId entryId) {
        return timeEntryCrudRepository.findById(entryId.value()).map(toTimeEntry());
    }

    private Function<TimeDoc, TimeEntry> toTimeEntry() {
        return timeDoc -> new TimeEntry(timeDoc.getHours(), new EntryId(timeDoc.getId()));
    }
}
