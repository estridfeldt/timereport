package se.emst.timereport.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import se.emst.timereport.domain.TimeEntry;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddTimeEntryRequest {
    private final BigDecimal hours;
    private final LocalDate date;

    @JsonCreator
    public AddTimeEntryRequest(@JsonProperty("hours") BigDecimal hours, @JsonProperty("date") LocalDate date) {
        this.hours = hours;
        this.date = date;
    }

    TimeEntry toTimeEntry() {
        return new TimeEntry(hours, null, date);
    }

    public BigDecimal getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
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
