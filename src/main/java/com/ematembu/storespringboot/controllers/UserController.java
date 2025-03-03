package com.ematembu.storespringboot.controllers;

import com.ematembu.storespringboot.models.User;
import com.ematembu.storespringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public ResponseEntity<?> findAllUsers() {
        List<User> users =  userService.getUsers();
        if(users.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "No users found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else {
            Map<String, List<User>> resp = new HashMap<>();
            resp.put("users", users);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    }

    @RequestMapping("/users/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable int userId) {
        User user = userService.findUserById(userId);
        if(user == null){
            Map<String, String> response = new HashMap<>();
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }
}
