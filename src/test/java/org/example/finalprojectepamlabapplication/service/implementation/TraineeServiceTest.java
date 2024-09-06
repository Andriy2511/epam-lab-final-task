package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DAO.ITraineeDAO;
import org.example.finalprojectepamlabapplication.model.Trainee;
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

public class TraineeServiceTest {

    private User user;
    private Trainee trainee;
    private List<User> defaultUserList;

    @Mock
    private ITraineeDAO traineeDAO;

    @Mock
    private IUserService userService;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("Test", "User", "", "", true);
        trainee = new Trainee(1L, new Date(), "Lviv", user);
        defaultUserList = new ArrayList<>();
        defaultUserList.add(new User("Ivan", "Ivanov", "", "", true));
        defaultUserList.add(new User("John", "Snow", "", "", true));

        when(userService.getAllUsers()).thenReturn(defaultUserList);
        when(traineeDAO.addTrainee(any(Trainee.class))).thenReturn(Optional.of(trainee));
    }

    @Test
    public void testAddTrainee() {
        trainee = traineeService.addTrainee(trainee).get();

        Assertions.assertNotNull(trainee);
        Assertions.assertEquals("Test.User", trainee.getUser().getUsername());
        Assertions.assertEquals(10, trainee.getUser().getPassword().length());
        verify(traineeDAO).addTrainee(trainee);
    }

    @Test
    public void testUpdateTrainee() {
        when(traineeDAO.updateTrainee(any(Trainee.class))).thenReturn(Optional.of(trainee));

        trainee = traineeService.updateTrainee(trainee).get();

        Assertions.assertNotNull(trainee);
        verify(traineeDAO).updateTrainee(trainee);
    }

    @Test
    public void testDeleteTrainee() {
        when(traineeDAO.deleteTrainee(anyLong())).thenReturn(Optional.of(trainee));

        trainee = traineeService.deleteTrainee(trainee).get();

        Assertions.assertNotNull(trainee);
        verify(traineeDAO).deleteTrainee(trainee.getId());
    }

    @Test
    public void testGetTraineeById() {
        when(traineeDAO.findById(anyLong())).thenReturn(Optional.of(trainee));

        trainee = traineeService.getTraineeById(1L).get();

        Assertions.assertNotNull(trainee);
        Assertions.assertEquals(1L, trainee.getId());
        verify(traineeDAO).findById(1L);
    }
}

