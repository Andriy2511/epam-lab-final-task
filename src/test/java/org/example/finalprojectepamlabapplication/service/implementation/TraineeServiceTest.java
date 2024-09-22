package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TraineeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingTypeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.repository.TraineeRepository;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.time.ZoneId;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TraineeServiceTest {

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private IUserService userService;

    @InjectMocks
    private TraineeService traineeService;

    private Trainee trainee;
    private TraineeDTO traineeDTO;
    TrainingTypeDTO trainingTypeDTO;
    List<TrainerDTO> trainers;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        trainee = new Trainee();

        trainee.setDateOfBirth(new Date());
        trainee.setAddress("Address");

        User user = new User();

        user.setFirstName("first");
        user.setLastName("user");
        user.setPassword("password123");
        trainee.setUser(user);

        UserDTO userDTO = UserDTO.builder()
                .id(2L)
                .firstName("second")
                .lastName("user")
                .username("second.user")
                .password("1234567891")
                .build();

        trainingTypeDTO = TrainingTypeDTO.builder()
                .id(1L)
                .trainingTypeName("Strength Training")
                .build();

        trainers = List.of(TrainerDTO.builder()
                .id(1L)
                .trainingTypeDTO(trainingTypeDTO)
                .userDTO(userDTO)
                .build());

        traineeDTO = TraineeDTO.builder()
                .id(1L)
                .dateOfBirth(new Date())
                .address("Address")
                .userDTO(UserDTO.toDTO(user))
                .build();
    }

    @Test
    public void testAddTrainee() {
        when(userService.getAllUsers()).thenReturn(List.of());
        when(traineeRepository.save(any(Trainee.class))).thenReturn(trainee);

        TraineeDTO result = traineeService.addTrainee(traineeDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("first.user", result.getUserDTO().getUsername());
    }

    @Test
    public void testUpdateTrainee() {
        when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
        when(userService.updateUser(any(UserDTO.class))).thenReturn(traineeDTO.getUserDTO());
        when(traineeRepository.save(any(Trainee.class))).thenReturn(trainee);

        traineeService.addTrainee(traineeDTO);

        Date oldDate = traineeDTO.getDateOfBirth();
        LocalDate localDate = LocalDate.of(2015, 1, 1);
        Date newDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        traineeDTO = traineeDTO.toBuilder().dateOfBirth(newDate).build();
        TraineeDTO result = traineeService.updateTrainee(traineeDTO);

        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(oldDate, result.getDateOfBirth());
    }

    @Test
    public void testDeleteTrainee() {
        when(traineeRepository.deleteTraineeById(anyLong())).thenReturn(Optional.of(trainee));

        TraineeDTO result = traineeService.deleteTrainee(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(trainee.getId(), result.getId());
    }

    @Test
    public void testGetTraineeById() {
        when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));

        TraineeDTO result = traineeService.getTraineeById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(trainee, TraineeDTO.toEntity(result));
    }

    @Test
    public void testUpdateTrainersListByTraineeId() {
        when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
        when(traineeRepository.save(any(Trainee.class))).thenReturn(trainee);

        TraineeDTO result = traineeService.updateTrainersListByTraineeId(trainers, 1L);

        Assertions.assertNotNull(result);
    }
}


