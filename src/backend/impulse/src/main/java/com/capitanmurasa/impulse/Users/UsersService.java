package com.capitanmurasa.impulse.Users;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository users;

    public UsersService(UserRepository users) {
        this.users = users;
    }

    @Transactional
    public UsersModel register(String username, String password, HttpSession session) {
        session.setAttribute("user", username);
        return users.save(new UsersModel(username, password));
    }

    @Transactional
    public Optional<UsersModel> login(String username, String password, HttpSession session){
        UsersModel userid = users.findByUsername(username);
        if(userid != null){
            if (userid.getPassword().equals(password)){
                session.setAttribute("user", username);
                return Optional.of(userid);
            }
            else{
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}
