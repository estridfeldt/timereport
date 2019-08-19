package se.emst.timereport.application;

import reactor.core.publisher.Mono;
import se.emst.timereport.domain.EntryId;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.util.List;

public class TimeEntryService {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryService(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public Mono<TimeEntry> addEntry(AddTimeEntryRequest timeEntryRequest) {
        TimeEntry timeEntry = timeEntryRequest.toTimeEntry();
        return timeEntryRepository.addEntry(timeEntry);
    }

    public Mono<List<TimeEntry>> getAllEntries() {
        return timeEntryRepository.findAll();
    }

    public Mono<TimeEntry> findOneEntry(EntryId entryId) {
        return timeEntryRepository.findOne(entryId);
    }
}
