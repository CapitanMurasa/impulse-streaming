package com.capitanmurasa.impulse.Users;

import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;


public interface UserRepository extends JpaRepository<UsersModel, Integer> {
    UsersModel findByUsername(String username);
}
