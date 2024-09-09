package org.example.finalprojectepamlabapplication.DAO.implementation;

import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.example.finalprojectepamlabapplication.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class TrainerDAOTest {

    private Map<Long, Trainer> trainerMap;
    private TrainerDAO trainerDAO;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        trainerMap = new HashMap<>();
        trainerDAO = new TrainerDAO(trainerMap);
        trainerMap.put(1L, new Trainer(1L, new TrainingType(1L, "Specialization 1"),
                new User("Test", "User", "Test.User", "password", true)));
        trainerMap.put(2L, new Trainer(2L, new TrainingType(2L,"Specialization 2"),
                new User("Test", "User", "Test.User1", "password2", true)));
        trainerMap.put(3L, new Trainer(3L, new TrainingType(3L,"Specialization 3"),
                new User("Test", "User", "Test.User2", "password3", true)));
        trainer = new Trainer(4L, new TrainingType(4L,"Specialization 4"),
                new User("Test", "User", "Test.User4", "password4", true));
    }

    @Test
    void findMaxIdTest() {
        Assertions.assertEquals(3L, trainerDAO.findMaxId());
    }

    @Test
    void findMaxIdWhenMapIsVoid() {
        trainerMap.clear();
        Assertions.assertEquals(0, trainerDAO.findMaxId());
    }

    @Test
    void addTrainerTest() {
        Optional<Trainer> result = trainerDAO.addTrainer(trainer);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(trainer, result.get());
    }

    @Test
    void updateTrainerTest() {
        Trainer updatedTrainer = new Trainer(1L, new TrainingType(5L, "Updated Specialization"),
                new User("Test", "User", "", "", true));

        Assertions.assertNotEquals(updatedTrainer, trainerMap.get(1L));

        trainerDAO.updateTrainer(updatedTrainer);

        Assertions.assertEquals(updatedTrainer, trainerMap.get(1L));
    }

    @Test
    void deleteTrainerTest() {
        trainer = trainerMap.get(1L);
        Trainer deletedTrainer = trainerDAO.deleteTrainer(1L).get();

        Assertions.assertEquals(trainer, deletedTrainer);
        Assertions.assertNull(trainerMap.get(1L));
    }

    @Test
    void findByIdTest() {
        trainer = trainerMap.get(1L);

        Assertions.assertEquals(trainer, trainerDAO.findById(1L).get());
    }

    @Test
    void findByIdWhenTrainerDoesntExistTest() {
        Assertions.assertTrue(trainerDAO.findById(99L).isEmpty());
    }
}
