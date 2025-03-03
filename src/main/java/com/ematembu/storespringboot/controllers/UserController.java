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
        User newUser = userService.registerUser(user);
        if(newUser != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        }else{
            Map<String, String> response = new HashMap<>();
            response.put("message", "User already exists!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @RequestMapping(value = "/users/{userId}", method= RequestMethod.PUT)
    public ResponseEntity<?> updateUserById(@PathVariable int userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        if(updatedUser != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }else{
            Map<String, String> response = new HashMap<>();
            response.put("message", "Failed to update user!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @RequestMapping(value="/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, String>> deleteUserById(@PathVariable int userId) {
        Map<String, String> response = new HashMap<>();
        User user = userService.findUserById(userId);
        if(user != null){
            userService.deleteUserById(userId);
            response.put("message", "User deleted successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
