package se.emst.timereport.domain;

import java.math.BigDecimal;

public class TimeEntryMother {
    public static TimeEntry defaultEntry() {
        return new TimeEntry(new BigDecimal("8"), new EntryId("22"));
    }
}
