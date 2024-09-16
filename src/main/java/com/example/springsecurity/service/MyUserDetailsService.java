package com.example.springsecurity.service;

import com.example.springsecurity.API.ApiException;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repoisory.AuthRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepositry authRepositry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepositry.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("wrong username or password");
        }



        return user ;

    }


















}
