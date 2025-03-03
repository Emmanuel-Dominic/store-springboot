package com.ematembu.storespringboot.services;

import com.ematembu.storespringboot.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    List<User> users = new ArrayList<>(Arrays.asList(
                new User(1, "Manuel", 30, "ematembu2@gmail.com", "Manuel123", "+256700701616", "Lungujja"),
                new User(2, "Dominic", 29, "ematembu@gmail.com", "Dominic123", "+256702701616", "Lungujja"),
                new User(3, "Timothy", 32, "timothy21@gmail.com", "Timothy123", "+256788084708", "Lungujja")
            ));

    public List<User> getUsers() {
        return users;
    }

    public User findUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null);
    }

    public User findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    public User registerUser(User user) {
        if (findUserByEmail(user.getEmail()) == null) {
            int id = users.size() + 1;
            user.setId(id);
            users.add(user);
            return user;
        }
        return null;
    }

    public User updateUser(int userId, User user) {
         for(int i=0; i<users.size(); i++){
             if(users.get(i).getId() == userId){
                 users.set(i, user);
                 return user;
             }
         }
         return null;
    }

    public void deleteUserById(int id) {
         users.removeIf(user -> user.getId() == id);
    }
}
