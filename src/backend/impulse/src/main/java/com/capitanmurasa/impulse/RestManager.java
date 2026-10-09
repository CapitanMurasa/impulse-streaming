package com.capitanmurasa.impulse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitanmurasa.impulse.Users.UsersService;

@RestController
// @RequestMapping("/api/v1")
public class RestManager {

    private final UsersService userService;

    public RestManager(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from impulse test!!";
    }

    @GetMapping("/login/{username}")
    public String login(@PathVariable String username, @RequestParam(defaultValue = "") String password){
        return Integer.toString(userService.login(username, password));
    }

    @GetMapping("/register/{username}")
    public String register(@PathVariable String username, @RequestParam(defaultValue = "") String password){
        return Integer.toString(userService.register(username, password));
    }

}