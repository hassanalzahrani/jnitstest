package com.example.springsecurity.service;

import com.example.springsecurity.API.ApiException;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repoisory.AuthRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepositry authRepositry;


    public List<User> getAllUsers() {
        return authRepositry.findAll();
    }

    public void register(User user) {
//اذا بضيف ادمن اضيفه اول شي قبل اليوزر
        user.setRole("ADMIN");
//        user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepositry.save(user);

    }

    public void updateUser(Integer user_id, User user) {
        User user1 = authRepositry.findUserById(user_id);
        if (user1 == null) {
            throw new ApiException("User not found");
        }

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
  user1.setPassword(hash);
       // user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        authRepositry.save(user1);


    }

    public void deleteUser(Integer admin_id, Integer user_id) {
        User user1 = authRepositry.findUserById(admin_id);
        User user2 = authRepositry.findUserById(user_id);
        if (user1 == null || user2 == null) {
            throw new ApiException("User not found");
        }
//      if (user1.getRole().equals("ADMIN")) {
//          authRepositry.deleteById(user_id);
//      }else throw new ApiException("User not admin");
//    }
        authRepositry.delete(user2);


    }


}
