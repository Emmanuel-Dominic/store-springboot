package com.ematembu.storespringboot.repository;

import com.ematembu.storespringboot.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void RegisterUserRepositoryTest(){
        User user = new User("Emmanuel", "ematembu2@gmail.com", "0712345678");
        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertNotEquals(0, newUser.getId());
    }

    @Test
    public void FindAllUserRepositoryTest(){
        User user1 = new User("Emmanuel", "ematembu@gmail.com", "0712345678");
        User user2 = new User("Dominic", "ematembu2@gmail.com", "0712345678");
        userRepository.save(user1);
        userRepository.save(user2);
        List<User> users = userRepository.findAll();

        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void FindOneUserRepositoryTest(){
        User user1 = new User("Emmanuel", "ematembu@gmail.com", "0712345678");
        User user2 = new User("Emmanuel", "ematembu2@gmail.com", "0712345678");
        userRepository.save(user1);
        Optional<User> findUser1 = userRepository.findById(user1.getId());
        Optional<User> findUser2 = userRepository.findById(user2.getId());

        Assertions.assertNotNull(findUser1);
        Assertions.assertTrue(findUser1.isPresent());
        Assertions.assertNotNull(findUser2);
        Assertions.assertFalse(findUser2.isPresent());
    }

    @Test
    public void UpdateUserRepositoryTest(){
        User user = new User("Emmanuel", "ematembu@gmail.com", "0712345678");
        userRepository.save(user);
        userRepository.findById(user.getId()).ifPresent(existingUser -> {
            existingUser.setName("Dominic");
            userRepository.save(existingUser);
        });
        User updateUser = userRepository.findById(user.getId()).orElseThrow();

        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals("Dominic", updateUser.getName());
    }

    @Test
    public void DeleteUserRepositoryTest(){
        User user = new User("Emmanuel", "ematembu@gmail.com", "0712345678");
        userRepository.save(user);
        userRepository.deleteById(user.getId());

        User updateUser1 = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNull(updateUser1);
        Optional<User> updateUser2 = userRepository.findById(user.getId());
        Assertions.assertFalse(updateUser2.isPresent());
    }
}
