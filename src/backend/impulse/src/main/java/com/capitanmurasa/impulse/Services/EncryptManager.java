package com.capitanmurasa.impulse.Services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptManager {
    private final BCryptPasswordEncoder passwordEncoder;
    public EncryptManager(){
         this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String EncryptPassword(String password){

        return passwordEncoder.encode(password);
    }

    public boolean DecryptPassword(String password, String encryptedPassword){
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
