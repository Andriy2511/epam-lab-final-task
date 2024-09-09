package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.ITraineeDAO;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.service.ITraineeService;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.example.finalprojectepamlabapplication.utility.PasswordGenerator;
import org.example.finalprojectepamlabapplication.utility.UsernameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TraineeService implements ITraineeService {

    private final ITraineeDAO traineeDAO;
    private final IUserService userService;
    private final UsernameGenerator usernameGenerator;

    @Autowired
    public TraineeService(ITraineeDAO traineeDAO, IUserService userService) {
        this.traineeDAO = traineeDAO;
        this.userService = userService;
        usernameGenerator = new UsernameGenerator();
    }

    @Override
    public Optional<Trainee> addTrainee(Trainee trainee) {
        trainee.setId(traineeDAO.findMaxId() + 1);
        User user = trainee.getUser();
        user.setUsername(usernameGenerator.generateUsername(user, userService.getAllUsers()));
        user.setPassword(PasswordGenerator.generatePassword());
        trainee.setUser(user);
        return traineeDAO.addTrainee(trainee);
    }

    @Override
    public Optional<Trainee> updateTrainee(Trainee trainee) {
        return traineeDAO.updateTrainee(trainee);
    }

    @Override
    public Optional<Trainee> deleteTrainee(Trainee trainee) {
        return traineeDAO.deleteTrainee(trainee.getId());
    }

    @Override
    public Optional<Trainee> getTraineeById(Long id) {
        log.info("Searching Trainee with id {}", id);
        Optional<Trainee> trainee = traineeDAO.findById(id);
        if (trainee.isEmpty()){
            log.warn("Trainee with id {} not found.", id);
        }
        return trainee;
    }
}
