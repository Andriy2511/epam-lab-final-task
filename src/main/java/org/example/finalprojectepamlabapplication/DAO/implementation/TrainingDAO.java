package org.example.finalprojectepamlabapplication.DAO.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.ITrainingDAO;
import org.example.finalprojectepamlabapplication.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class TrainingDAO implements ITrainingDAO {

    private final Map<Long, Training> trainingMap;

    @Autowired
    public TrainingDAO(Map<Long, Training> trainingMap) {
        this.trainingMap = trainingMap;
    }

    @Override
    public Optional<Training> addTraining(Training training) {
        trainingMap.put(training.getTraineeId(), training);
        log.info("Training for trainee with id {} has been added", training.getTraineeId());
        return Optional.of(training);
    }

    @Override
    public Optional<Training> findById(Long id) {
        return Optional.ofNullable(trainingMap.get(id));
    }
}
