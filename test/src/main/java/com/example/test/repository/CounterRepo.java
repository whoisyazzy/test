package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entity.*;

import java.util.Optional;

@Repository
public interface CounterRepo extends JpaRepository<Counter, Long>{
    Optional<Counter> findByName(String name);

    void deleteByName(String name);
}
