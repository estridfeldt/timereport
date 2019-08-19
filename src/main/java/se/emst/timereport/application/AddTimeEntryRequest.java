package se.emst.timereport.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import se.emst.timereport.domain.TimeEntry;

import java.math.BigDecimal;

public class AddTimeEntryRequest {
    private final BigDecimal hours;

    @JsonCreator
    public AddTimeEntryRequest(@JsonProperty("hours") BigDecimal hours) {
        this.hours = hours;
    }

    TimeEntry toTimeEntry() {
        return new TimeEntry(hours, null);
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
}
