package se.emst.timereport.application;

import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Mono;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.math.BigDecimal;
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
        List<TimeEntry> expectedEntries = Collections.singletonList(new TimeEntry(new BigDecimal("5")));

        given(timeEntryRepository.findAll()).willReturn(Mono.just(expectedEntries));

        //when
        Mono<List<TimeEntry>> actualEntries = timeEntryService.getAllEntries();

        //then
        assertThat(actualEntries.block()).isEqualTo(expectedEntries);
    }

    @Test
    public void shouldAddOneEntry() {
        //given
        TimeEntry timeEntry = new TimeEntry(new BigDecimal("6"));

        //when
        timeEntryService.addEntry(timeEntry);

        //then
        verify(timeEntryRepository).addEntry(timeEntry);
    }
}