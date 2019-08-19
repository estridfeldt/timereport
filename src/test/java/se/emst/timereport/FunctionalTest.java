package se.emst.timereport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldGetEmptyListOfEntries() {
        getEntries().json("[]");
    }

    @Test
    public void shouldAddOneEntry() throws JsonProcessingException {
        //given
        TimeReportEntry entry = new TimeReportEntry(new BigDecimal("8"));

        //when
        addOneEntry(entry);

        //then
        getEntries().json(new ObjectMapper().writeValueAsString(Collections.singletonList(entry)));
    }

    private void addOneEntry(TimeReportEntry entry) {
        webTestClient.post().uri("/entries")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(entry))
                .exchange()
                .expectStatus()
                .isCreated();
    }

    private WebTestClient.BodyContentSpec getEntries() {
        return webTestClient.get().uri("/entries")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    private class TimeReportEntry {
        private final BigDecimal hours;

        TimeReportEntry(BigDecimal hours) {
            this.hours = hours;
        }

        @SuppressWarnings("unused")
        public BigDecimal getHours() {
            return hours;
        }

        @Override
        public String toString() {
            return "TimeReportEntry{" +
                    "hours=" + hours +
                    '}';
        }
    }
}
