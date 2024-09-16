package com.example.springsecurity.controller;

import com.example.springsecurity.model.User;
import com.example.springsecurity.service.AuthService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user) {
        authService.register(user);
        return ResponseEntity.status(200).body("user registered successfully");
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity updateUser(@PathVariable Integer user_id,@Valid @RequestBody User user) {
        authService.updateUser(user_id,user);
        return ResponseEntity.status(200).body("user updated successfully");
    }
    @DeleteMapping("/delete/{admin_id}/{user_id}")
    public ResponseEntity deleteUser(@PathVariable Integer admin_id,@PathVariable  Integer user_id) {
        authService.deleteUser(admin_id,user_id);
        return ResponseEntity.status(200).body("user deleted successfully");
    }
}
