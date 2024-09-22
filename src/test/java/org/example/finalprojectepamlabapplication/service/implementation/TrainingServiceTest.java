package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.*;
import org.example.finalprojectepamlabapplication.model.*;
import org.example.finalprojectepamlabapplication.repository.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;

    private TrainingDTO trainingDTO;
    private TraineeDTO traineeDTO;
    private TrainerDTO trainerDTO;
    private TrainingTypeDTO trainingTypeDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        UserDTO traineeUserDTO = UserDTO.builder()
                .id(1L)
                .firstName("TraineeFirst")
                .lastName("TraineeLast")
                .username("trainee.username")
                .build();

        traineeDTO = TraineeDTO.builder()
                .id(1L)
                .userDTO(traineeUserDTO)
                .dateOfBirth(new Date())
                .address("123 Trainee Street")
                .build();

        UserDTO trainerUserDTO = UserDTO.builder()
                .id(2L)
                .firstName("TrainerFirst")
                .lastName("TrainerLast")
                .username("trainer.username")
                .build();

        trainingTypeDTO = TrainingTypeDTO.builder()
                .id(1L)
                .trainingTypeName("Yoga")
                .build();

        trainerDTO = TrainerDTO.builder()
                .id(1L)
                .userDTO(trainerUserDTO)
                .trainingTypeDTO(trainingTypeDTO)
                .build();

        trainingDTO = TrainingDTO.builder()
                .id(1L)
                .trainee(traineeDTO)
                .trainer(trainerDTO)
                .trainingName("Morning Yoga")
                .trainingTypeDTO(trainingTypeDTO)
                .trainingDate(new Date())
                .trainingDuration(60)
                .build();
    }


    @Test
    public void testAddTraining() {
        when(trainingRepository.save(any(Training.class))).thenReturn(TrainingDTO.toEntity(trainingDTO));

        TrainingDTO result = trainingService.addTraining(trainingDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(trainingDTO.getId(), result.getId());
        Assertions.assertEquals("Morning Yoga", result.getTrainingName());
    }

    @Test
    public void testGetTrainingById() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findById(anyLong())).thenReturn(Optional.of(training));

        TrainingDTO result = trainingService.getTrainingById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Morning Yoga", result.getTrainingName());
    }

    @Test
    public void testGetTrainingByName() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingByTrainingName(anyString())).thenReturn(Optional.of(training));

        TrainingDTO result = trainingService.getTrainingByName("Morning Yoga");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Morning Yoga", result.getTrainingName());
    }

    @Test
    public void testGetTrainingsByTraineeUsernameAndToDate() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingsByTraineeUsernameAndToDate(anyString(), any(Date.class)))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTraineeUsernameAndToDate("trainee.username", new Date());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Morning Yoga", result.get(0).getTrainingName());
    }

    @Test
    public void testGetTrainingsByTraineeUsernameAndFromDate() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingsByTraineeUsernameAndFromDate(anyString(), any(Date.class)))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTraineeUsernameAndFromDate("trainee.username", new Date());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Morning Yoga", result.get(0).getTrainingName());
    }

    @Test
    public void testGetTrainingsByTraineeUsernameAndTrainingType() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        TrainingType trainingType = TrainingTypeDTO.toEntity(trainingTypeDTO);
        when(trainingRepository.findTrainingsByTraineeUsernameAndTrainingType(anyString(), any(TrainingType.class)))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTraineeUsernameAndTrainingType("trainee.username", trainingType);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Yoga", result.get(0).getTrainingTypeDTO().getTrainingTypeName());
    }

    @Test
    public void testGetTrainingsByTraineeUsernameAndTrainerUsername() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingsByTraineeUsernameAndTrainerUsername(anyString(), anyString()))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTraineeUsernameAndTrainerUsername("trainee.username", "trainer.username");

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("trainer.username", result.get(0).getTrainer().getUserDTO().getUsername());
    }

    @Test
    public void testGetTrainingsByTrainerUsernameAndToDate() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingsByTrainerUsernameAndToDate(anyString(), any(Date.class)))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTrainerUsernameAndToDate("trainer.username", new Date());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("trainer.username", result.get(0).getTrainer().getUserDTO().getUsername());
    }

    @Test
    public void testGetTrainingsByTrainerUsernameAndFromDate() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingsByTrainerUsernameAndFromDate(anyString(), any(Date.class)))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTrainerUsernameAndFromDate("trainer.username", new Date());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("trainer.username", result.get(0).getTrainer().getUserDTO().getUsername());
    }

    @Test
    public void testGetTrainingsByTrainerUsernameAndTrainingType() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        TrainingType trainingType = TrainingTypeDTO.toEntity(trainingTypeDTO);
        when(trainingRepository.findTrainingsByTrainerUsernameAndTrainingType(anyString(), any(TrainingType.class)))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTrainerUsernameAndTrainingType("trainer.username", trainingType);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Yoga", result.get(0).getTrainingTypeDTO().getTrainingTypeName());
    }

    @Test
    public void testGetTrainingsByTrainerUsernameAndTraineeUsername() {
        Training training = TrainingDTO.toEntity(trainingDTO);
        when(trainingRepository.findTrainingsByTrainerUsernameAndTraineeUsername(anyString(), anyString()))
                .thenReturn(List.of(training));

        List<TrainingDTO> result = trainingService.getTrainingsByTrainerUsernameAndTraineeUsername("trainer.username", "trainee.username");

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("trainee.username", result.get(0).getTrainee().getUserDTO().getUsername());
    }
}
