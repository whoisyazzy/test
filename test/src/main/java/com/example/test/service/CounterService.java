package com.example.test.service;
import com.example.test.entity.*;
import com.example.test.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CounterService {

    private final CounterRepo counterRepo;

    @Autowired
    public CounterService(CounterRepo counterRepo){
        this.counterRepo = counterRepo;
    }
    
    public ResponseEntity<String> savCounter(Counter counter){
        Optional<Counter> temp = counterRepo.findByName(counter.getName());
        if(temp.isPresent()){
            throw new RuntimeException("Counter already exists");
        }
        if(counter.getCount() == 0){
            counter.setCount(1);
            return ResponseEntity.ok("Counter has to be positive. It has been set to 1");
        }
        counterRepo.save(counter);
        return ResponseEntity.ok().build();


    }

    public Map<String, Integer> getAllCounters(){
        Map<String, Integer> allCounters = new HashMap<>();
        List<Counter> LCounters = counterRepo.findAll();
        for (Counter counter : LCounters){
            allCounters.put(counter.getName(),counter.getCount());
        }
        return allCounters;
    }

    public Map<String,Integer> getCounterbyName(String name){
        Map <String, Integer> counter = new HashMap<>();
        Optional <Counter> curr = counterRepo.findByName(name);
        if (curr.isPresent()){
        Counter counterF = curr.get();
        counter.put(counterF.getName(),counterF.getCount());}
        else{
            throw new RuntimeException("Counter does not exist");
        }
        return counter;
    }

    public Counter incrementCounter(String name){
        Optional<Counter> curr = counterRepo.findByName(name);
        if (curr.isPresent()){
            Counter counter = curr.get();
            counter.setCount(counter.getCount()+1);
            return counterRepo.save(counter);
    
        } else
        {
            throw new RuntimeException("Counter not found");
        }
    }
    @Transactional
    public void decrementCounter(String name){
        Optional<Counter> curr = counterRepo.findByName(name);
        if (curr.isPresent()){
            Counter counter = curr.get();
            int value = counter.getCount();
            if (value <= 0){
                counterRepo.deleteByName(name);
            }
            else{
                counter.setCount(value-1);
                counterRepo.save(counter);
            }
        } else throw new RuntimeException("Counter does not exists");

    }
}
