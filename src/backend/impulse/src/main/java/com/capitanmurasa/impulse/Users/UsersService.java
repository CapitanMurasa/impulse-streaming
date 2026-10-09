package com.capitanmurasa.impulse.Users;


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
    public int register(String username, String password) {
        users.save(new UsersModel(username, password));
        return 200;
    }

    @Transactional
    public int login(String username, String password){
        UsersModel userid = users.findByUsername(username);
        if(userid == null){
            return 401;
        }
        else if (userid.getPassword().equals(password)){
            return 200;
        }
        else {
            return 401;
        }
    }
}
