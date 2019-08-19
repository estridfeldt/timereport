package se.emst.timereport.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document
class TimeDoc {

    @Id
    private String id;
    private BigDecimal hours;
    private LocalDate date;

    TimeDoc(BigDecimal hours, LocalDate date) {
        this.hours = hours;
        this.date = date;
    }

    String getId() {
        return id;
    }

    BigDecimal getHours() {
        return hours;
    }

    LocalDate getDate() {
        return date;
    }
}
