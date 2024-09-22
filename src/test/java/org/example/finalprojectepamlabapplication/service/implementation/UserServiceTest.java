package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TraineeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.repository.UserRepository;
import org.example.finalprojectepamlabapplication.utility.UsernameGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setUsername("jane.smith");
        user.setPassword("password123");
        user.setActive(true);

        userDTO = UserDTO.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Smith")
                .username("jane.smith")
                .password("password123")
                .isActive(true)
                .build();
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.getAllUsers();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(user.getId(), result.get(0).getId());
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDTO.getId(), result.getId());
    }

    @Test
    public void testGetUserByUsername() {
        when(userRepository.getUserByUsername(anyString())).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserByUsername("jane.smith");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDTO.getUsername(), result.getUsername());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userDTO = userDTO.toBuilder().lastName("Doe").build();
        UserDTO result = userService.updateUser(userDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Doe", result.getLastName());
        Assertions.assertEquals("Jane.Doe", result.getUsername());
    }

    @Test
    public void testUpdateUserPassword() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userService.updateUserPassword(userDTO, "newPassword");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("newPassword", result.getPassword());
    }

    @Test
    public void testChangeActiveStatus() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO result = userService.changeActiveStatus(1L);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isActive());
    }
}
