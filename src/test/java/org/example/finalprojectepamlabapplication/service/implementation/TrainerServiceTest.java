package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DAO.ITrainerDAO;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    private User user;
    private TrainingType trainingType;
    private Trainer trainer;
    private List<User> defaultUserList;

    @Mock
    private ITrainerDAO trainerDAO;

    @Mock
    private IUserService userService;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("Test", "Trainer", "", "", true);
        trainingType = new TrainingType(1L, "Fitness");

        trainer = new Trainer(1L, trainingType, user);

        defaultUserList = new ArrayList<>();
        defaultUserList.add(new User("Ivan", "Ivanov", "", "", true));
        defaultUserList.add(new User("John", "Snow", "", "", true));

        when(userService.getAllUsers()).thenReturn(defaultUserList);
    }

    @Test
    public void testAddTrainer() {
        when(trainerDAO.addTrainer(any(Trainer.class))).thenReturn(Optional.of(trainer));

        trainer = trainerService.addTrainer(trainer).get();

        Assertions.assertNotNull(trainer);
        Assertions.assertEquals("Test.Trainer", trainer.getUser().getUsername());
        Assertions.assertEquals(10, trainer.getUser().getPassword().length());
    }

    @Test
    public void testUpdateTrainer() {
        when(trainerDAO.updateTrainer(any(Trainer.class))).thenReturn(Optional.of(trainer));

        trainer = trainerService.updateTrainer(trainer).get();

        Assertions.assertNotNull(trainer);
    }

    @Test
    public void testDeleteTrainer() {
        when(trainerDAO.deleteTrainer(anyLong())).thenReturn(Optional.of(trainer));

        trainer = trainerService.deleteTrainer(trainer).get();

        Assertions.assertNotNull(trainer);
    }

    @Test
    public void testGetTrainerById() {
        when(trainerDAO.findById(anyLong())).thenReturn(Optional.of(trainer));

        trainer = trainerService.getTrainerById(1L).get();

        Assertions.assertNotNull(trainer);
        Assertions.assertEquals(1L, trainer.getId());
    }
}

