package se.emst.timereport.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
