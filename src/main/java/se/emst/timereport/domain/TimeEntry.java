package se.emst.timereport.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TimeEntry {

    private final BigDecimal hours;

    @JsonCreator
    public TimeEntry(@JsonProperty("hours") BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getHours() {
        return hours;
    }
}
