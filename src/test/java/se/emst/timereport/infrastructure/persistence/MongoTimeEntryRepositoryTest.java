package se.emst.timereport.infrastructure.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import se.emst.timereport.domain.TimeEntry;
import se.emst.timereport.domain.TimeEntryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MongoTimeEntryRepositoryTest {

    private static final LocalDate A_DATE = LocalDate.of(2012, 11, 21);

    @Autowired
    private TimeEntryCrudRepository timeEntryCrudRepository;

    @Test
    public void shouldGetNewlyCreatedEntry() {
        //given
        TimeEntryRepository repository = new MongoTimeEntryRepository(timeEntryCrudRepository);
        TimeEntry createdEntry = repository.addEntry(new TimeEntry(BigDecimal.TEN, null, A_DATE)).block();

        //when
        TimeEntry retrievedEntry = repository.findOne(createdEntry.getId()).block();

        //then
        assertThat(createdEntry).isEqualTo(retrievedEntry);
    }

    @Test
    public void shouldGetAllNewlyCreatedEntries() {
        //given
        TimeEntryRepository repository = new MongoTimeEntryRepository(timeEntryCrudRepository);
        TimeEntry createdEntry = repository.addEntry(new TimeEntry(BigDecimal.TEN, null, A_DATE)).block();

        //when
        List<TimeEntry> retrievedEntry = repository.findAll().block();

        //then
        assertThat(Collections.singletonList(createdEntry)).isEqualTo(retrievedEntry);
    }

}
