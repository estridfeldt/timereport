package se.emst.timereport.application;

import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Mono;
import se.emst.timereport.domain.EntryId;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryMother;
import se.emst.timereport.domain.TimeEntryRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class TimeEntryServiceTest {

    private TimeEntryRepository timeEntryRepository;
    private TimeEntryService timeEntryService;

    @Before
    public void setUp() {
        timeEntryRepository = mock(TimeEntryRepository.class);
        timeEntryService = new TimeEntryService(timeEntryRepository);
    }

    @Test
    public void shouldGetEntries() {
        //given
        List<TimeEntry> expectedEntries = Collections.singletonList(TimeEntryMother.defaultEntry());

        given(timeEntryRepository.findAll()).willReturn(Mono.just(expectedEntries));

        //when
        Mono<List<TimeEntry>> actualEntries = timeEntryService.getAllEntries();

        //then
        assertThat(actualEntries.block()).isEqualTo(expectedEntries);
    }

    @Test
    public void shouldAddOneEntry() {
        //given
        TimeEntry expectedEntry = TimeEntryMother.defaultEntry();
        AddTimeEntryRequest timeEntryRequest = new AddTimeEntryRequest(expectedEntry.getHours());
        given(timeEntryRepository.addEntry(timeEntryRequest.toTimeEntry())).willReturn(Mono.just(expectedEntry));

        //when
        Mono<TimeEntry> timeEntry = timeEntryService.addEntry(timeEntryRequest);

        //then
        assertThat(timeEntry.block()).isEqualTo(expectedEntry);
    }

    @Test
    public void shouldFindOneEntry() {
        //given
        TimeEntry expectedEntry = TimeEntryMother.defaultEntry();
        EntryId entryId = new EntryId("4");
        given(timeEntryRepository.findOne(entryId)).willReturn(Mono.just(expectedEntry));

        //when
        Mono<TimeEntry> actualEntry = timeEntryService.findOneEntry(entryId);

        //then
        assertThat(actualEntry.block()).isEqualTo(expectedEntry);
    }
}