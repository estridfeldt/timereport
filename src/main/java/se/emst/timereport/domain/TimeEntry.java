package se.emst.timereport.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimeEntry {

    private final BigDecimal hours;
    private final EntryId entryId;
    private final LocalDate date;

    @JsonCreator
    public TimeEntry(@JsonProperty("hours") BigDecimal hours,
                     @JsonProperty("id") EntryId entryId,
                     @JsonProperty("date") LocalDate date) {
        this.hours = hours;
        this.entryId = entryId;
        this.date = date;
    }

    public BigDecimal getHours() {
        return hours;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public EntryId getId() {
        return entryId;
    }

    public LocalDate getDate() {
        return date;
    }
}
