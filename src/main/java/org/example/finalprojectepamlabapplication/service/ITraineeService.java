package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.TraineeDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainerDTO;
import org.example.finalprojectepamlabapplication.model.Trainee;

import java.util.List;
import java.util.Optional;

public interface ITraineeService {

    TraineeDTO addTrainee(TraineeDTO traineeDTO);

    TraineeDTO updateTrainee(TraineeDTO traineeDTO);

    TraineeDTO deleteTrainee(Long id);

    TraineeDTO getTraineeById(Long id);

    TraineeDTO updateTrainersListByTraineeId(List<TrainerDTO> trainerDTOList, Long traineeId);
}
