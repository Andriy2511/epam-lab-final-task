package org.example.finalprojectepamlabapplication.DAO.implementation;

import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class TrainingDAOTest {

    private Map<Long, Training> trainingMap;
    private TrainingDAO trainingDAO;
    private Training training;

    @BeforeEach
    void setUp() {
        trainingMap = new HashMap<>();
        trainingDAO = new TrainingDAO(trainingMap);
        trainingMap.put(1L, new Training(1L, 1L, 1L, "Training 1", new TrainingType(1L, "Type 1"), new Date(), 60));
        trainingMap.put(2L, new Training(2L, 2L, 2L, "Training 2", new TrainingType(2L, "Type 2"), new Date(), 90));
        trainingMap.put(3L, new Training(3L, 3L, 3L, "Training 3", new TrainingType(3L, "Type 3"), new Date(), 120));
        training = new Training(4L, 4L, 4L, "Training 4", new TrainingType(4L, "Type 4"), new Date(), 150);
    }

    @Test
    void findMaxIdTest() {
        Assertions.assertEquals(3L, trainingDAO.findMaxId());
    }

    @Test
    void findMaxIdWhenMapIsVoid() {
        trainingMap.clear();
        Assertions.assertEquals(0, trainingDAO.findMaxId());
    }

    @Test
    void addTrainingTest() {
        Optional<Training> result = trainingDAO.addTraining(training);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(training, result.get());
    }

    @Test
    void findByIdTest() {
        training = trainingMap.get(1L);

        Assertions.assertEquals(training, trainingDAO.findById(1L).get());
    }

    @Test
    void findByIdWhenTrainingDoesntExistTest() {
        Assertions.assertTrue(trainingDAO.findById(99L).isEmpty());
    }

}
