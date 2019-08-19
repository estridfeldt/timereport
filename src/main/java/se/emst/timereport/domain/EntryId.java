package se.emst.timereport.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EntryId {
    private final String value;

    public EntryId(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }

    public String value() {
        return value;
    }
}
