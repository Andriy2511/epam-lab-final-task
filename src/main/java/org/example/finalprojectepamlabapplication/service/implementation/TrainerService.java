package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingTypeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.repository.TrainerRepository;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.service.ITrainerService;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.example.finalprojectepamlabapplication.utility.PasswordGenerator;
import org.example.finalprojectepamlabapplication.utility.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainerService implements ITrainerService {

    private final TrainerRepository trainerRepository;
    private final IUserService userService;
    private final UsernameGenerator usernameGenerator;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, IUserService userService) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
        usernameGenerator = new UsernameGenerator();
    }

    @Override
    public TrainerDTO addTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = TrainerDTO.toEntity(trainerDTO);
        User user = trainer.getUser();
        user.setUsername(usernameGenerator.generateUsername(user, userService.getAllUsers()));
        user.setPassword(PasswordGenerator.generatePassword());
        trainer.setUser(user);
        return TrainerDTO.toDTO(trainerRepository.save(trainer));
    }

    @Override
    public TrainerDTO updateTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.findById(trainerDTO.getId()).orElseThrow();
        trainer.setTrainingType(TrainingTypeDTO.toEntity(trainerDTO.getTrainingTypeDTO()));
        trainer.setUser(UserDTO.toEntity(userService.updateUser(trainerDTO.getUserDTO())));
        return TrainerDTO.toDTO(trainerRepository.save(trainer));
    }

    @Override
    public TrainerDTO deleteTrainer(Long id) {
        return TrainerDTO.toDTO(trainerRepository.deleteTrainerById(id).orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public TrainerDTO getTrainerById(Long id) {
        log.info("Searching Trainer with id {}", id);
        Optional<Trainer> trainer = trainerRepository.findById(id);
        if (trainer.isEmpty()){
            log.warn("Trainer with id {} not found.", id);
        }

        return TrainerDTO.toDTO(trainer.orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainerDTO> getTrainersNotAssignedToTrainee(String traineeUsername) {
        List<Trainer> trainers = trainerRepository.findTrainersWhichNotAssignToTraineeByUsername(traineeUsername);
        return trainers.stream().map(TrainerDTO::toDTO).toList();
    }
}
