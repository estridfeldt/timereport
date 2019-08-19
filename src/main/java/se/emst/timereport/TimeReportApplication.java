package se.emst.timereport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.emst.timereport.application.TimeEntryService;
import se.emst.timereport.infrastructure.persistence.MongoTimeEntryRepository;
import se.emst.timereport.infrastructure.persistence.TimeEntryCrudRepository;

@SpringBootApplication
public class TimeReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeReportApplication.class, args);
    }

    @Bean
    public TimeEntryService timeEntryService(TimeEntryCrudRepository timeEntryCrudRepository) {
        return new TimeEntryService(new MongoTimeEntryRepository(timeEntryCrudRepository));
    }
}
