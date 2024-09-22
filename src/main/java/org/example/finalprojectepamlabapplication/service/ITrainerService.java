package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.model.Trainer;

import java.util.List;
import java.util.Optional;

public interface ITrainerService {

    TrainerDTO addTrainer(TrainerDTO trainerDTO);

    TrainerDTO updateTrainer(TrainerDTO trainerDTO);

    TrainerDTO deleteTrainer(Long id);

    TrainerDTO getTrainerById(Long id);

    List<TrainerDTO> getTrainersNotAssignedToTrainee(String traineeUsername);
}
