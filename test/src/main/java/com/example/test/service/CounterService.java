package com.example.test.service;
import com.example.test.entity.*;
import com.example.test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CounterService {

    private final CounterRepo counterRepo;

    @Autowired
    public CounterService(CounterRepo counterRepo){
        this.counterRepo = counterRepo;
    }
    
    public Counter savCounter(Counter counter){
        return counterRepo.save(counter);
    }

    public List<Counter> getAllCounters(){
        return counterRepo.findAll();
    }

    public Optional<Counter> getCounterbyID(Long id){
        return counterRepo.findById(id);
    }

    public Counter updateCounter(Long id, Counter upd_Counter){
        Optional<Counter> curr = counterRepo.findById(id);
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

    public void deleteCounter(Long id){
        counterRepo.deleteById(id);
    }
}
