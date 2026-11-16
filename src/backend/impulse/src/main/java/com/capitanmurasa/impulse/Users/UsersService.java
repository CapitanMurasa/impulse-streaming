package com.capitanmurasa.impulse.Users;

import jakarta.servlet.http.HttpSession;

import com.capitanmurasa.impulse.Shared.EncryptManager;
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
    public UsersModel register(String username, String password, HttpSession session) {
        //session.setAttribute("user", username);
        return users.save(new UsersModel(username, encrypt.EncryptPassword(password), "user", username));
    }

    @Transactional
    public Optional<UsersModel> login(String username, String password, HttpSession session){
        Optional<UsersModel> userid = users.findByUsername(username);
        if (session.getAttribute("user") == null) {
            if (userid.isPresent()) {
                if (encrypt.DecryptPassword(password, userid.get().getPassword())) {
                    session.setAttribute("user", username);
                    return userid;
                } else {
                    return Optional.empty();
                }
            }

            return Optional.empty();
        }
        else {
            return Optional.empty();
        }
    }
}
