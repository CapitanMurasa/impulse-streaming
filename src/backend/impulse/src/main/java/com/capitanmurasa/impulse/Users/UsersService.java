package com.capitanmurasa.impulse.Users;


import com.capitanmurasa.impulse.Services.EncryptManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository users;
    private final EncryptManager encrypt;

    public UsersService(UserRepository users) {

        this.users = users;

        this.encrypt = new EncryptManager();
    }

    @Transactional
    public int register(String username, String password) {
        users.save(new UsersModel(username, encrypt.EncryptPassword(password)));
        return 200;
    }

    @Transactional
    public int login(String username, String password){
        UsersModel userid = users.findByUsername(username);
        if(userid == null){
            return 401;
        }
        else if (encrypt.DecryptPassword(password, userid.getPassword())){
            return 200;
        }
        else {
            return 401;
        }
    }
}
