package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingDTO;
import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.model.TrainingType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITrainingService {

    TrainingDTO addTraining(TrainingDTO trainingDTO);

    TrainingDTO getTrainingById(Long id);

    TrainingDTO getTrainingByName(String name);

    List<TrainingDTO> getTrainingsByTraineeUsernameAndToDate(String username, Date toDate);

    List<TrainingDTO> getTrainingsByTraineeUsernameAndFromDate(String username, Date fromDate);

    List<TrainingDTO> getTrainingsByTraineeUsernameAndTrainingType(String username, TrainingType trainingType);

    List<TrainingDTO> getTrainingsByTraineeUsernameAndTrainerUsername(String username, String trainerUsername);

    List<TrainingDTO> getTrainingsByTrainerUsernameAndToDate(String username, Date toDate);

    List<TrainingDTO> getTrainingsByTrainerUsernameAndFromDate(String username, Date fromDate);

    List<TrainingDTO> getTrainingsByTrainerUsernameAndTrainingType(String username, TrainingType trainingType);

    List<TrainingDTO> getTrainingsByTrainerUsernameAndTraineeUsername(String username, String traineeUsername);
}
