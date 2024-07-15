package com.nagam.poc.customer_transaction_management.repository;

import com.nagam.poc.customer_transaction_management.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        // Clean up any existing data before each test
        userRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        // No need to manually delete data here
        // Spring will automatically roll back transactions at the end of each test
    }

    @Test
    public void testSaveUser() {
        // Given
        User user = new User("testuser", "testuser@example.com", "password", new HashSet<>());

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertNotNull(savedUser.getId());
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    public void testFindByUsername() {
        // Given
        User user = new User("testuser", "testuser@example.com", "password", new HashSet<>());
        userRepository.save(user);

        // When
        Optional<User> optionalUser = userRepository.findByUsername("testuser");

        // Then
        assertTrue(optionalUser.isPresent());
        assertEquals("testuser", optionalUser.get().getUsername());
    }

    @Test
    public void testFindByUsername_NotFound() {
        // When
        Optional<User> optionalUser = userRepository.findByUsername("nonexistentuser");

        // Then
        assertFalse(optionalUser.isPresent());
    }

    @Test
    public void testExistsByUsername_True() {
        // Given
        User user = new User("testuser", "testuser@example.com", "password", new HashSet<>());
        userRepository.save(user);

        // When
        boolean exists = userRepository.existsByUsername("testuser");

        // Then
        assertTrue(exists);
    }

    @Test
    public void testExistsByUsername_False() {
        // When
        boolean exists = userRepository.existsByUsername("nonexistentuser");

        // Then
        assertFalse(exists);
    }

    @Test
    public void testUniqueConstraints() {
        // Given
        User user1 = new User("testuser1", "testuser1@example.com", "password", new HashSet<>());
        userRepository.save(user1);

        // Attempt to save user2 with the same username
        User user2 = new User("testuser1", "testuser2@example.com", "password", new HashSet<>());

        // When, Then
        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user2));

        // Assert other conditions if necessary
        assertNotNull(exception);
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User("testuser", "testuser@example.com", "password", new HashSet<>());
        User savedUser = userRepository.save(user);

        // When
        userRepository.delete(savedUser);

        // Then
        Optional<User> deletedUser = userRepository.findByUsername("testuser");
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testFindByEmail() {
        // Given
        User user1 = new User("testuser1", "testuser1@example.com", "password", new HashSet<>());
        User user2 = new User("testuser2", "testuser2@example.com", "password", new HashSet<>());
        userRepository.save(user1);
        userRepository.save(user2);

        // When
        Optional<User> foundUser1 = userRepository.findByEmail("testuser1@example.com");
        Optional<User> foundUser2 = userRepository.findByEmail("testuser2@example.com");

        // Then
        assertTrue(foundUser1.isPresent());
        assertEquals("testuser1", foundUser1.get().getUsername());

        assertTrue(foundUser2.isPresent());
        assertEquals("testuser2", foundUser2.get().getUsername());
    }
}