package com.example.test.service;

import com.example.test.entity.Counter;
import com.example.test.repository.CounterRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import lombok.Builder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CounterServiceTest {

    @Mock
    CounterRepo counterRepo;
    @InjectMocks
    CounterService counterService;

    @Test
    void should_return_all_created_counters() {
        Counter counter = Counter.builder()
                .name("abc")
                .count(4)
                .build();
        Counter counter1 = Counter.builder()
                .name("xyz")
                .count(3)
                .build();
        when(counterRepo.findAll()).thenReturn(List.of(counter,counter1));

        Map<String, Integer> result = counterService.getAllCounters();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(4,result.get("abc"));
        assertEquals(3,result.get("xyz"));
    }

    @Test
    void should_create_a_new_counter() {
        String name = "abc";
        Counter counter = Counter.builder()
                .name(name)
                .count(4)
                .build();

        when(counterRepo.save(counter)).thenReturn(counter);

        ResponseEntity<String> result = counterService.savCounter(counter);

        assertNotNull(result);
        assertEquals(200,result.getStatusCode().value());
    }

    @Test
    void should_increment_the_counter_by_one() {
        String name = "abc";
        Counter counter = Counter.builder()
                .name(name)
                .count(4)
                .build();
        when(counterRepo.findByName(name)).thenReturn(Optional.of(counter));

        counterService.incrementCounter(name);

        assertEquals(5,counter.getCount());
    }

    @Test
    void should_decrement_the_counter() {
        String name = "abc";
        Counter counter = Counter.builder()
                .name(name)
                .count(4)
                .build();

        when(counterRepo.findByName(name)).thenReturn(Optional.of(counter));

        counterService.decrementCounter(name);

        assertEquals(3,counter.getCount());
    }

    @Test
    void should_return_the_counter_by_name() {
        String name = "abc";
        Counter counter = Counter.builder()
                .name(name)
                .count(4)
                .build();

        when(counterRepo.findByName(name)).thenReturn(Optional.of(counter));

        Map<String, Integer> result = counterService.getCounterbyName(name);


        assertEquals(counter.getCount(),result.get(name));
        assertTrue(result.containsKey(name));


    }
}