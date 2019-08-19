package se.emst.timereport.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimeEntryMother {
    public static TimeEntry defaultEntry() {
        return new TimeEntry(new BigDecimal("8"), new EntryId("22"), LocalDate.of(2011,2,13));
    }
}
