package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingDTO;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    TrainingDTO addTraining(TrainingDTO trainingDTO);

    TrainingDTO getTrainingById(Long id);

    TrainingDTO getTrainingByName(String name);

    List<TrainingDTO> getTrainingsByTraineeAndCriteria(Long traineeId, Date toDate, Date fromDate,
                                                       TrainingType trainingType, String trainerUsername);

    List<TrainingDTO> getTrainingsByTrainerAndCriteria(Long trainerId, Date toDate, Date fromDate,
                                                       TrainingType trainingType, String traineeUsername);
}
