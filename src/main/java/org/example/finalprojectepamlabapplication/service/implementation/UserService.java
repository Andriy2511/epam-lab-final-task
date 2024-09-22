package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TraineeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.repository.UserRepository;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.example.finalprojectepamlabapplication.utility.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UsernameGenerator usernameGenerator;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.usernameGenerator = new UsernameGenerator();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Searching all users. Total users: {}", users.size());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        return getUserDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow();

        return getUserDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        if(!userDTO.getFirstName().equals(user.getFirstName()) || !userDTO.getLastName().equals(user.getLastName())) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setUsername(usernameGenerator.generateUsername(user, userRepository.findAll()));
        }
        userRepository.save(user);
        return UserDTO.toDTO(user);
    }

    @Override
    public UserDTO updateUserPassword(UserDTO userDTO, String password) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        user.setPassword(password);
        userRepository.save(user);
        return UserDTO.toDTO(user);
    }

    @Override
    public UserDTO changeActiveStatus(Long id){
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(!user.isActive());
        userRepository.save(user);
        return UserDTO.toDTO(user);
    }

    private UserDTO getUserDTO(User user){
        UserDTO userDTO = UserDTO.toDTO(user);

        Optional.ofNullable(user.getTrainee())
                .ifPresent(trainee -> userDTO.toBuilder().traineeDTO(TraineeDTO.toDTO(trainee)));

        Optional.ofNullable(user.getTrainer())
                .ifPresent(trainer -> userDTO.toBuilder().trainerDTO(TrainerDTO.toDTO(trainer)));

        return userDTO;
    }
}
