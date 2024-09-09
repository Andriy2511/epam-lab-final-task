package org.example.finalprojectepamlabapplication.DAO.implementation;

import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class TraineeDAOTest {

    private Map<Long, Trainee> traineeMap;
    private TraineeDAO traineeDAO;
    private Trainee trainee;

    @BeforeEach
    void setUp() {
        traineeMap = new HashMap<>();
        traineeDAO = new TraineeDAO(traineeMap);
        traineeMap.put(1L, new Trainee(1L, new Date(), "Address 1", new User("Test", "User", "Test.User", "password", true)));
        traineeMap.put(2L, new Trainee(2L, new Date(), "Address 2", new User("Test", "User", "Test.User1", "password2", true)));
        traineeMap.put(3L, new Trainee(3L, new Date(), "Address 3", new User("Test", "User", "Test.User2", "password3", true)));
        trainee = new Trainee(4L, new Date(), "Address", new User("Test", "User", "Test.User4", "password4", true));
    }

    @Test
    void findMaxIdTest() {
        Assertions.assertEquals(3L, traineeDAO.findMaxId());
    }

    @Test
    void findMaxIdWhenMapIsVoid() {
        traineeMap.clear();
        Assertions.assertEquals(0, traineeDAO.findMaxId());
    }

    @Test
    void addTraineeTest() {
        Optional<Trainee> result = traineeDAO.addTrainee(trainee);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(trainee, result.get());
    }

    @Test
    void updateTraineeTest() {
        Trainee updatedTrainee = new Trainee(1L, new Date(), "Updated Address",
                new User("Test", "User", "", "", true));

        Assertions.assertNotEquals(updatedTrainee, traineeMap.get(1L));

        traineeDAO.updateTrainee(updatedTrainee);

        Assertions.assertEquals(updatedTrainee, traineeMap.get(1L));
    }

    @Test
    void deleteTraineeTest() {
        trainee = traineeMap.get(1L);
        Trainee deletedTrainee = traineeDAO.deleteTrainee(1L).get();

        Assertions.assertEquals(trainee, deletedTrainee);
        Assertions.assertNull(traineeMap.get(1L));
    }

    @Test
    void findByIdTest() {
        trainee = traineeMap.get(1L);

        Assertions.assertEquals(trainee, traineeDAO.findById(1L).get());
    }

    @Test
    void findByIdWhenUserDoesntExistTest() {
        Assertions.assertTrue(traineeDAO.findById(99L).isEmpty());
    }

}
