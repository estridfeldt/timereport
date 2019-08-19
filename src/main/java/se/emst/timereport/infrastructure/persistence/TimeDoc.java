package se.emst.timereport.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
class TimeDoc {

    @Id
    private String id;
    private BigDecimal hours;

    TimeDoc(BigDecimal hours) {
        this.hours = hours;
    }

    String getId() {
        return id;
    }

    BigDecimal getHours() {
        return hours;
    }
}
