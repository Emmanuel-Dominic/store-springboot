package com.ematembu.storespringboot.services;

import com.ematembu.storespringboot.models.User;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    List<User> users = Arrays.asList(
                new User(1, "Manuel", 30, "ematembu2@gmail.com", "Manuel123", "+256700701616", "Lungujja"),
                new User(2, "Dominic", 29, "ematembu@gmail.com", "Dominic123", "+256702701616", "Lungujja"),
                new User(3, "Timothy", 32, "timothy21@gmail.com", "Timothy123", "+256788084708", "Lungujja")
            );

    public List<User> getUsers() {
        return users;
    }

    public User findUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null);
    }
}
