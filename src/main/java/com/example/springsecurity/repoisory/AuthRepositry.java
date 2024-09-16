package com.example.springsecurity.repoisory;

import com.example.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepositry extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByUsername(String username);
}
