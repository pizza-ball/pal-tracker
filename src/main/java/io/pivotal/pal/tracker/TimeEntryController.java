package io.pivotal.pal.tracker;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {

    public TimeEntryRepository timeEntryRepo;

    public TimeEntryController(TimeEntryRepository repo)
    {
        timeEntryRepo = repo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry)
    {
        TimeEntry entryResult = timeEntryRepo.create(entry);
        return ResponseEntity.created(null).body(entryResult);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry entryResult = timeEntryRepo.find(timeEntryId);
        if(entryResult == null)
            return new ResponseEntity<>(entryResult, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(entryResult, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> entryResult = timeEntryRepo.list();
        return new ResponseEntity<>(entryResult, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry entryResult = timeEntryRepo.update(timeEntryId, timeEntryToUpdate);
        if(entryResult == null)
            return new ResponseEntity<>(entryResult, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(entryResult, HttpStatus.OK);
    }


    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepo.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
