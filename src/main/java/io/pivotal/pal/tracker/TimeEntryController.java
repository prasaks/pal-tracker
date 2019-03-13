package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tracker.TimeEntry;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {


    private TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry entry = timeEntryRepository.create(timeEntryToCreate);

        if (entry == null) {
            return new ResponseEntity(entry, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(entry, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {

        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);


        if (timeEntry == null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> entries = timeEntryRepository.list();
        if (entries == null) {
            return new ResponseEntity<List<TimeEntry>>(entries, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<TimeEntry>>(entries, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);

        if (timeEntry == null) {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {

        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}