package se.emst.timereport.application;

import reactor.core.publisher.Mono;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.util.List;

public class TimeEntryService {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryService(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public void addEntry(TimeEntry timeEntry) {
        timeEntryRepository.addEntry(timeEntry);
    }

    public Mono<List<TimeEntry>> getAllEntries() {
        return timeEntryRepository.findAll();
    }
}
