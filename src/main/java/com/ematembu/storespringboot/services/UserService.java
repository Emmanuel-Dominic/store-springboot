package com.ematembu.storespringboot.services;

import com.ematembu.storespringboot.models.User;
import com.ematembu.storespringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).orElse(null) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public User updateUser(int userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            if (!existingUser.getEmail().equals(user.getEmail())
                    && userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new RuntimeException("Email already in use");
            }
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            existingUser.setAge(user.getAge());
            existingUser.setPhone(user.getPhone());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
