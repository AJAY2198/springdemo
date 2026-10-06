package com.example.demo.greeting;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    private final GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping  
    public Iterable<Greeting> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Greeting create(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Request body must include a non-blank 'name'.");
        }
        return service.create(name.trim());
    }
}
