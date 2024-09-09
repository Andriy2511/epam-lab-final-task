package org.example.finalprojectepamlabapplication.DAO.implementation;

import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOTest {

    private Map<Long, Trainee> traineeMap;
    private Map<Long, Trainer> trainerMap;
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        traineeMap = new HashMap<>();
        trainerMap = new HashMap<>();

        traineeMap.put(1L, new Trainee(1L, null, "Address 1", new User("Test", "User", "Test.User", "password", true)));
        traineeMap.put(2L, new Trainee(2L, null, "Address 2", new User("Test", "User", "Test.User1", "password2", true)));

        trainerMap.put(1L, new Trainer(1L, null, new User("Test", "User", "Test.User2", "password3", true)));
        trainerMap.put(2L, new Trainer(2L, null, new User("Test", "User", "Test.User3", "password4", true)));

        userDAO = new UserDAO(traineeMap, trainerMap);
    }

    @Test
    void findAllUsersTest() {
        List<User> expectedUsers = Arrays.asList(
                new User("Test", "User", "Test.User", "password", true),
                new User("Test", "User", "Test.User1", "password2", true),
                new User("Test", "User", "Test.User2", "password3", true),
                new User("Test", "User", "Test.User3", "password4", true)
        );

        List<User> result = userDAO.findAllUsers();

        assertEquals(expectedUsers, result);
    }
}
