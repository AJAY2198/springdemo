package com.example.demo.greeting;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final GreetingRepository repository;
    private final GreetingFormatter formatter;

    public GreetingService(GreetingRepository repository, GreetingFormatter formatter) {
        this.repository = repository;
        this.formatter = formatter;
    }

    public Greeting create(String name) {
        return repository.save(new Greeting(formatter.format(name)));
    }

    public Iterable<Greeting> findAll() {
        return repository.findAll();
    }
}
