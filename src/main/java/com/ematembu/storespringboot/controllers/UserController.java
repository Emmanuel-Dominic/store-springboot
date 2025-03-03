package com.ematembu.storespringboot.controllers;

import com.ematembu.storespringboot.models.User;
import com.ematembu.storespringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method= RequestMethod.GET)
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

    @RequestMapping(value = "/users", method= RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try{
            User newUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
