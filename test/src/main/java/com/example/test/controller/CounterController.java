package com.example.test.controller;



import com.example.test.entity.*;
import com.example.test.service.CounterService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/test")
public class CounterController {

    private final CounterService counterService;

    
    public CounterController(CounterService counterService){
        this.counterService = counterService;
    }


    //GET /counters/  - List of currently active counters in a map/dictionary format ({ "abc": 5, "xyz": 3 }) //Key:name Values:counter
    @GetMapping("/counters")
    public Map<String, Integer> getAllCounters() {
        Map<String, Integer> allCounters = counterService.getAllCounters();
        return allCounters;
    }
    



    // POST /counters Creates a new counter with an initial value { "counter": initialValue }

// - validate input schema

// - initialValue should be positive

// - if counter exists already throw an exception

    @PostMapping("/counters")
    public ResponseEntity<Counter> saveCounter(@Valid @RequestBody Counter counter) {
        counterService.savCounter(counter);
        return ResponseEntity.ok().build();
    }
    



    // PUT /counters/{counter}

//     - Increases a counter value by one (no body required).

// - Fails if counter does not exist (404 Not found)
    @PutMapping("counters/{name}")
    public ResponseEntity<Counter> updateCounter(@Valid @PathVariable String name){
        counterService.incrementCounter(name);
        return ResponseEntity.ok().build();
    }
    



     // DEL /counters/{counter} - Decreases a counter value by one, if value <= 0 the counter disappears.
// - Does fail if the counter does not exist.
    @DeleteMapping("/counters/{name}")
    public ResponseEntity<String> deleteCounter(@PathVariable String name){
        counterService.decrementCounter(name);
        return ResponseEntity.ok().build();
    }

    // GET /counters/{counter}
//     - Returns value of counter {"counter1": 5}. // - Fails if the counter does not exist (404 Not found)

    @GetMapping("/counters/{name}")
    public Map<String,Integer> getCounterbyName(@PathVariable String name) {
        return counterService.getCounterbyName(name);
    }
    



    
};
