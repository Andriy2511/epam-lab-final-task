package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.implementation.TrainingDAO;
import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.service.ITrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TrainingService implements ITrainingService {

    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingService(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public Optional<Training> addTraining(Training training) {
        log.info("Adding training: {}", training.getTrainingName());
        return trainingDAO.addTraining(training);
    }

    @Override
    public Optional<Training> getTrainingById(Long id) {
        log.info("Searching training with id: {}", id);
        return trainingDAO.findById(id);
    }
}
