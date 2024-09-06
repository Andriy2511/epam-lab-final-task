package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.ITrainerDAO;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.service.ITrainerService;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.example.finalprojectepamlabapplication.utility.PasswordGenerator;
import org.example.finalprojectepamlabapplication.utility.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TrainerService implements ITrainerService {

    private final ITrainerDAO trainerDAO;
    private final IUserService userService;
    private final UsernameGenerator usernameGenerator;

    @Autowired
    public TrainerService(ITrainerDAO trainerDAO, IUserService userService) {
        this.trainerDAO = trainerDAO;
        this.userService = userService;
        usernameGenerator = new UsernameGenerator();
    }

    @Override
    public Optional<Trainer> addTrainer(Trainer trainer) {
        User user = trainer.getUser();
        user.setUsername(usernameGenerator.generateUsername(user, userService.getAllUsers()));
        user.setPassword(PasswordGenerator.generatePassword());
        trainer.setUser(user);
        log.info("Adding Trainer with id: {}", trainer.getId());
        return trainerDAO.addTrainer(trainer);
    }

    @Override
    public Optional<Trainer> updateTrainer(Trainer trainer) {
        log.info("Updating Trainer with id: {}", trainer.getId());
        return trainerDAO.updateTrainer(trainer);
    }

    @Override
    public Optional<Trainer> deleteTrainer(Trainer trainer) {
        log.info("Deleting Trainer with id {}", trainer.getId());
        return trainerDAO.deleteTrainer(trainer.getId());
    }

    @Override
    public Optional<Trainer> getTrainerById(Long id) {
        log.info("Searching Trainer with id {}", id);
        return trainerDAO.findById(id);
    }
}
