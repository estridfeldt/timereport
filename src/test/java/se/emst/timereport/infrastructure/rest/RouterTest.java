package se.emst.timereport.infrastructure.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import se.emst.timereport.application.AddTimeEntryRequest;
import se.emst.timereport.application.TimeEntryService;
import se.emst.timereport.domain.EntryId;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryMother;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        List<TimeEntry> expectedEntries = Collections.singletonList(TimeEntryMother.defaultEntry());
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
        TimeEntry timeEntry = TimeEntryMother.defaultEntry();
        AddTimeEntryRequest addTimeEntryRequest = new AddTimeEntryRequest(timeEntry.getHours());
        given(timeEntryService.addEntry(addTimeEntryRequest)).willReturn(Mono.just(timeEntry));

        //when
        client.post().uri("/entries")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(addTimeEntryRequest))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().value("location", s -> assertThat(s).isEqualTo("/entries/" + timeEntry.getId().value()))
                .expectBody(TimeEntry.class).isEqualTo(timeEntry);


    }

    @Test
    public void shouldGetOneEntry() {
        //given
        TimeEntry expectedEntry = TimeEntryMother.defaultEntry();
        given(timeEntryService.findOneEntry(expectedEntry.getId())).willReturn(Mono.just(expectedEntry));

        //when
        client.get().uri("/entries/{id}", expectedEntry.getId().value())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeEntry.class)
                .isEqualTo(expectedEntry);
    }
}