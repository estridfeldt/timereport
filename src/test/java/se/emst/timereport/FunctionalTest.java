package se.emst.timereport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

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
        TimeReportEntry entry = new TimeReportEntry(new BigDecimal("8"), LocalDate.of(2013,2,10));

        //when

        addOneEntry(entry);

        //then
        getEntries().json(new ObjectMapper().writeValueAsString(Collections.singletonList(entry)));
    }

    private void addOneEntry(TimeReportEntry entry) {
        EntityExchangeResult<TimeReportEntry> result = webTestClient.post().uri("/entries")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(entry))
                .exchange().expectStatus().isCreated()
                .expectBody(TimeReportEntry.class)
                .isEqualTo(entry)
                .returnResult();

        URI location = result.getResponseHeaders().getLocation();
        EntityExchangeResult<TimeReportEntry> getOneResult = webTestClient.get().uri(location)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TimeReportEntry.class)
                .isEqualTo(entry)
                .returnResult();
        assertThat(result.getResponseBody()).isEqualTo(getOneResult.getResponseBody());
    }

    private WebTestClient.BodyContentSpec getEntries() {
        return webTestClient.get().uri("/entries")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    private static class TimeReportEntry {
        private final BigDecimal hours;
        private final LocalDate date;

        @JsonCreator
        TimeReportEntry(@JsonProperty("hours") BigDecimal hours, @JsonProperty("date") LocalDate date) {
            this.hours = hours;
            this.date = date;
        }

        @SuppressWarnings("unused")
        public BigDecimal getHours() {
            return hours;
        }

        @SuppressWarnings("unused")
        public String getDate() {
            return date.toString();
        }

        @Override
        public String toString() {
            return "TimeReportEntry{" +
                    "hours=" + hours +
                    ", date=" + date +
                    '}';
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }
    }
}
