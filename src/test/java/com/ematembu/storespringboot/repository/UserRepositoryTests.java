package com.ematembu.storespringboot.repository;

import com.ematembu.storespringboot.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void RegisterUserRepositoryTest(){
        User user = new User("Emmanuel", 28, "ematembu2@gmail.com", "Password123", "0712345678", "kampala");
        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertNotEquals(0, newUser.getId());
    }
}
