package com.capitanmurasa.impulse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/api/v1")
public class RestManager {

    @GetMapping("/")
    public String index() {
        return "Greetings from impulse test!!";
    }

    @GetMapping("/login/{id}")
    public String greeting(@PathVariable int id, @RequestParam(defaultValue = "") String password){
        return Integer.toString(id) + " " + password;
    }

}