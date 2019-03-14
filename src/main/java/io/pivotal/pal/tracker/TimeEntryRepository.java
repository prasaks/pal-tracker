package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry timeEntry);


    List<TimeEntry> list();

    TimeEntry update(long eq, TimeEntry timeEntry);


    void delete(long timeEntryId);

    TimeEntry find(Long id);
}
