package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DAO.implementation.TrainingDAO;
import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TrainingServiceTest {

    private Training training;
    private TrainingType trainingType;

    @Mock
    TrainingDAO trainingDAO;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        trainingType = new TrainingType(1L, "Strength Training");
        training = new Training(1L, 1L, 1L, "Morning Strength Training", trainingType, new Date(), 60);
    }

    @Test
    public void testAddTraining() {
        when(trainingDAO.addTraining(any(Training.class))).thenReturn(Optional.of(training));

        Optional<Training> result = trainingService.addTraining(training);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(training, result.get());
        Assertions.assertEquals("Morning Strength Training", result.get().getTrainingName());
    }

    @Test
    public void testGetTrainingById() {
        Optional<Training> result = trainingService.getTrainingById(1L);

        Assertions.assertNotNull(result);
    }
}

