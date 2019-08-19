package se.emst.timereport.infrastructure.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import se.emst.timereport.application.TimeEntryService;
import se.emst.timereport.domain.TimeEntry;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class RouterTest {

    private WebTestClient client;
    private TimeEntryService timeEntryService;

    @Before
    public void setUp() {
        timeEntryService = mock(TimeEntryService.class);
        Router router = new Router(timeEntryService);
        client = WebTestClient.bindToRouterFunction(router.routers()).build();
    }

    @Test
    public void shouldGetAllEntries() {
        //given
        List<TimeEntry> expectedEntries = Collections.singletonList(new TimeEntry(new BigDecimal("6")));
        given(timeEntryService.getAllEntries()).willReturn(Mono.just(expectedEntries));

        //when
        //then
        client.get().uri("/entries")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TimeEntry.class)
                .isEqualTo(expectedEntries);
    }

    @Test
    public void shouldAddOneEntry() {
        //given
        TimeEntry timeEntry = new TimeEntry(new BigDecimal("4"));

        //when
        client.post().uri("/entries")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(timeEntry))
                .exchange()
                .expectStatus().isCreated();

        //then
        verify(timeEntryService).addEntry(timeEntry);
    }
}