package ca.softwarespace.riot.dataaggregator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/")
    public String greetings() {
        return "Hello from";
    }
}
