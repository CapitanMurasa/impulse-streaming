package com.capitanmurasa.impulse.Services;



import com.capitanmurasa.impulse.Beans.DomainBean;
import com.capitanmurasa.impulse.Beans.JsonResponseStatus;
import com.capitanmurasa.impulse.Users.UsersModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.capitanmurasa.impulse.Users.UsersService;

import java.util.ArrayList;
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
    public ResponseEntity<DomainBean> login(@PathVariable String username, @RequestParam(defaultValue = "") String password, HttpSession session){
        ResponseEntity<DomainBean> entity;
        DomainBean status = new DomainBean();
        HttpHeaders headers = new HttpHeaders();
        Optional<UsersModel> loginReq = userService.login(username, password, session);

        if (!loginReq.equals(Optional.empty())){
            status.setResponseStatus(JsonResponseStatus.OK);
            status.setName(username);
            status.setData("You successfully logged in!");
            entity = new ResponseEntity<>(status,headers, HttpStatus.CREATED);
            return entity;
        }
        else{
            status.setResponseStatus(JsonResponseStatus.FAIL);
            status.setData("Can't login...");
            entity = new ResponseEntity<>(status,headers, HttpStatus.NOT_FOUND);
            return entity;
        }
    }

    @GetMapping("/userpage")
    public ResponseEntity<DomainBean> userpage(HttpSession session){
        ResponseEntity<DomainBean> entity;
        DomainBean status = new DomainBean();
        HttpHeaders headers = new HttpHeaders();
        String user = (String) session.getAttribute("user");
        status.setResponseStatus(JsonResponseStatus.OK);
        if (user == null){
            status.setName("none!");
            status.setData("You didn't login into api!");
        }
        else{
            status.setName(user);
            status.setData("Welcome back! " + user);
        }
        entity = new ResponseEntity<>(status,headers, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/logout")
    public ResponseEntity<DomainBean> logout(HttpSession session){
        ResponseEntity<DomainBean> entity;
        HttpHeaders headers = new HttpHeaders();
        DomainBean status = new DomainBean();

        status.setResponseStatus(JsonResponseStatus.OK);
        session.invalidate();

        entity = new ResponseEntity<>(status,headers, HttpStatus.OK);

        return entity;
    }

    @GetMapping("/register/{username}")
    public ResponseEntity<DomainBean> register(@PathVariable String username, @RequestParam(defaultValue = "") String password, HttpSession session){
        ResponseEntity<DomainBean> entity;
        DomainBean status = new DomainBean();
        HttpHeaders headers = new HttpHeaders();
        UsersModel registerReq = userService.register(username, password, session);



        if(registerReq.getName().equals(username)){
            status.setResponseStatus(JsonResponseStatus.OK);
            status.setName(username);
            status.setData("User Created successfully!");
            entity = new ResponseEntity<>(status,headers, HttpStatus.CREATED);
            return entity;
        }
        else{
            status.setResponseStatus(JsonResponseStatus.FAIL);
            status.setData("Error in creating user...");
            entity = new ResponseEntity<>(status,headers, HttpStatus.INTERNAL_SERVER_ERROR);
            return entity;
        }
    }

}