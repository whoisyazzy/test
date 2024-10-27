package com.example.test.service;
import com.example.test.entity.*;
import com.example.test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public Counter savCounter(Counter counter){
        Optional<Counter> temp = counterRepo.findByName(counter.getName());
        if(temp.isPresent()){ throw new RuntimeException("Counter already exists");
        }


        return counterRepo.save(counter);
    }

    public Map<String, Integer> getAllCounters(){
        Map<String, Integer> allCounters = new HashMap<>();
        List<Counter> LCounters = counterRepo.findAll();
        for (Counter counter : LCounters){
            allCounters.put(counter.getName(),counter.getCount());
        }
        return allCounters;
    }

    public Optional<Counter> getCounterbyName(String name){
        return counterRepo.findByName(name);
    }

    public Counter updateCounter(String name, Counter upd_Counter){
        Optional<Counter> curr = counterRepo.findByName(name);
        if (curr.isPresent()){
            Counter counter = curr.get();
            counter.setName(upd_Counter.getName());
            counter.setCount(upd_Counter.getCount());
            return counterRepo.save(counter);
    
        } else
        {
            throw new RuntimeException("Counter not found");
        }
    }

    public void deleteCounter(String name){
        counterRepo.deleteByName(name);
    }
}
