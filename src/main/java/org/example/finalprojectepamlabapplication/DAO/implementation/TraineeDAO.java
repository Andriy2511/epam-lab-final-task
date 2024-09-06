package org.example.finalprojectepamlabapplication.DAO.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.ITraineeDAO;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class TraineeDAO implements ITraineeDAO {

    private final Map<Long, Trainee> traineeMap;

    @Autowired
    public TraineeDAO(Map<Long, Trainee> traineeMap){
        this.traineeMap = traineeMap;
    }

    @Override
    public Optional<Trainee> addTrainee(Trainee trainee) {
        traineeMap.put(trainee.getId(), trainee);
        log.info("Trainee with id {} has been added", trainee.getId());
        return Optional.of(trainee);
    }

    @Override
    public Optional<Trainee> updateTrainee(Trainee trainee) {
        log.info("Trainee with id {} has been changed", trainee.getId());
        return Optional.ofNullable(traineeMap.replace(trainee.getId(), trainee));
    }

    @Override
    public Optional<Trainee> findById(Long id) {
        Optional<Trainee> searchedTrainee = Optional.ofNullable(traineeMap.get(id));
        if (searchedTrainee.isEmpty())
            log.warn("Searched Trainee with id {} not found.", id);
        return searchedTrainee;
    }

    @Override
    public Optional<Trainee> deleteTrainee(Long id) {
        Optional<Trainee> removedTrainee = Optional.ofNullable(traineeMap.remove(id));
        if (removedTrainee.isEmpty())
            log.warn("Failed to delete Trainee with id {}. Trainee not found.", id);
        return removedTrainee;
    }
}
