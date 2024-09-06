package org.example.finalprojectepamlabapplication.DAO.implementation;

import org.example.finalprojectepamlabapplication.DAO.IUserDAO;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class UserDAO implements IUserDAO {

    private final Map<Long, Trainee> traineeMap;
    private final Map<Long, Trainer> trainerMap;

    @Autowired
    public UserDAO(Map<Long, Trainee> traineeMap, Map<Long, Trainer> trainerMap) {
        this.traineeMap = traineeMap;
        this.trainerMap = trainerMap;
    }

    @Override
    public List<User> findAllUsers() {
        return Stream.concat(
                traineeMap.values().stream().map(Trainee::getUser),
                trainerMap.values().stream().map(Trainer::getUser)
        ).toList();
    }
}
