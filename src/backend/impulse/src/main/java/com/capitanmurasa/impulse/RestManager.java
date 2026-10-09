package com.capitanmurasa.impulse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestManager {

    @GetMapping("/")
    public String index() {
        return "Greetings from impulse test!!";
    }

}