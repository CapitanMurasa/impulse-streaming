package com.capitanmurasa.impulse;



import com.capitanmurasa.impulse.Users.UsersModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import com.capitanmurasa.impulse.Users.UsersService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
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
    public String login(@PathVariable String username, @RequestParam(defaultValue = "") String password, HttpSession session){
        Optional<UsersModel> loginReq = userService.login(username, password, session);
        if (!loginReq.equals(Optional.empty())){
            return Integer.toString(200);
        }
        else{
            return Integer.toString(401);
        }
    }

    @GetMapping("/userpage")
    public String userpage(HttpSession session){
        String user = (String) session.getAttribute("user");
        if (user == null){
            return "not logged in";
        }
        return user;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "bye!";
    }

    @GetMapping("/register/{username}")
    public String register(@PathVariable String username, @RequestParam(defaultValue = "") String password, HttpSession session){
        UsersModel registerReq = userService.register(username, password, session);
        if(registerReq.getName().equals(username)){
            return Integer.toString(200);
        }
        else{
            return Integer.toString(423);
        }
    }

}