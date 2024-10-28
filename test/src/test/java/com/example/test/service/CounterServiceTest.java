package com.example.test.service;

import com.example.test.repository.CounterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CounterServiceTest {

    @Autowired
    private CounterRepo counterRepo;

    private CounterService counterService;

    @Test
    void CounterService_saveCounter() {
    }

    @Test
    void CounterService_getAllCounters() {
    }

    @Test
    void CounterService_getCounterbyName() {
    }

    @Test
    void CounterService_incrementCounter() {
    }

    @Test
    void CounterService_decrementCounter() {
    }
}