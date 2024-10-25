package com.example.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int count_value;

    // Getters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getCount(){
        return count_value;
    }
    // Setters
    public void setId(Long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setCount(int count){
        this.count_value=count;
    }
};

