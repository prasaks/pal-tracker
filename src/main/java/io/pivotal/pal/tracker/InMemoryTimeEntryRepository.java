package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private final Map<Long, TimeEntry> inMemoryDb = new HashMap<>();
    private long timeEntryId = 0L;


    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        ++timeEntryId;
        TimeEntry entry = new TimeEntry(timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        inMemoryDb.put(timeEntryId, entry);
        return entry;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(inMemoryDb.values());
    }

    @Override
    public TimeEntry update(long eq, TimeEntry timeEntry) {

        TimeEntry entry = inMemoryDb.get(eq);

        if (entry == null) {
            return null;
        }
        entry.setProjectId(timeEntry.getProjectId());
        entry.setUserId(timeEntry.getUserId());
        entry.setHours(timeEntry.getHours());
        entry.setDate(timeEntry.getDate());
        return inMemoryDb.get(eq);
    }

    @Override
    public void delete(long timeEntryId) {

        inMemoryDb.remove(timeEntryId);
    }

    @Override
    public TimeEntry find(Long id) {
        return inMemoryDb.get(id);
    }
}
