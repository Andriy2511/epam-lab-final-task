package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingTypeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.repository.TrainerRepository;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TrainerServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private IUserService userService;

    @InjectMocks
    private TrainerService trainerService;

    private Trainer trainer;
    private TrainerDTO trainerDTO;
    private TrainingTypeDTO trainingTypeDTO;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setId(1L);
        user.setFirstName("first");
        user.setLastName("user");
        user.setUsername("first.user");
        user.setPassword("password123");

        userDTO = UserDTO.builder()
                .id(1L)
                .firstName("second")
                .lastName("user")
                .username("second.user")
                .password("password123")
                .build();

        TrainingType trainingType = new TrainingType();
        trainingType.setId(1L);
        trainingType.setTrainingTypeName("Yoga");

        trainingTypeDTO = TrainingTypeDTO.builder()
                .id(1L)
                .trainingTypeName("Yoga")
                .build();

        trainer = new Trainer();
        trainer.setId(1L);
        trainer.setUser(user);
        trainer.setTrainingType(trainingType);

        trainerDTO = TrainerDTO.builder()
                .id(1L)
                .userDTO(userDTO)
                .trainingTypeDTO(trainingTypeDTO)
                .build();
    }

    @Test
    public void testAddTrainer() {
        when(userService.getAllUsers()).thenReturn(List.of());
        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);

        TrainerDTO result = trainerService.addTrainer(trainerDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(trainerDTO.getTrainingTypeDTO().getId(), result.getTrainingTypeDTO().getId());
        Assertions.assertEquals(trainerDTO.getTrainingTypeDTO().getTrainingTypeName(), result.getTrainingTypeDTO().getTrainingTypeName());
    }

    @Test
    public void testUpdateTrainer() {
        when(trainerRepository.findById(anyLong())).thenReturn(Optional.of(trainer));
        when(userService.updateUser(any(UserDTO.class))).thenReturn(userDTO);
        when(trainerRepository.save(any(Trainer.class))).thenReturn(trainer);

        trainerDTO = trainerDTO.toBuilder().trainingTypeDTO(trainingTypeDTO.toBuilder().trainingTypeName("Pilates").build()).build();
        TrainerDTO result = trainerService.updateTrainer(trainerDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Pilates", result.getTrainingTypeDTO().getTrainingTypeName());
    }

    @Test
    public void testDeleteTrainer() {
        when(trainerRepository.deleteTrainerById(anyLong())).thenReturn(Optional.of(trainer));

        TrainerDTO result = trainerService.deleteTrainer(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(trainerDTO.getId(), result.getId());
    }

    @Test
    public void testGetTrainerById() {
        when(trainerRepository.findById(anyLong())).thenReturn(Optional.of(trainer));

        TrainerDTO result = trainerService.getTrainerById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(trainerDTO.getId(), result.getId());
    }

    @Test
    public void testGetTrainersNotAssignedToTrainee() {
        when(trainerRepository.findTrainersWhichNotAssignToTraineeByUsername(anyString())).thenReturn(List.of(trainer));

        List<TrainerDTO> result = trainerService.getTrainersNotAssignedToTrainee("trainee.username");

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(trainerDTO.getId(), result.get(0).getId());
    }
}
