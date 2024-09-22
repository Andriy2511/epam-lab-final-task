package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingDTO;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.example.finalprojectepamlabapplication.repository.TrainingRepository;
import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.service.ITrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrainingService implements ITrainingService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public TrainingDTO addTraining(TrainingDTO trainingDTO) {
        Training training = TrainingDTO.toEntity(trainingDTO);
        return TrainingDTO.toDTO(trainingRepository.save(training));
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingDTO getTrainingById(Long id) {
        log.info("Searching training with id: {}", id);
        Optional<Training> training = trainingRepository.findById(id);
        if (training.isEmpty()){
            log.warn("Training with id {} not found.", id);
        }
        return TrainingDTO.toDTO(training.orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingDTO getTrainingByName(String name) {
        Optional<Training> training = trainingRepository.findTrainingByTrainingName(name);
        if (training.isEmpty()){
            log.warn("Training with name {} not found.", training);
        }
        return TrainingDTO.toDTO(training.orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTraineeUsernameAndToDate(String username, Date toDate){
        List<Training> trainings = trainingRepository.findTrainingsByTraineeUsernameAndToDate(username, toDate);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTraineeUsernameAndFromDate(String username, Date fromDate) {
        List<Training> trainings = trainingRepository.findTrainingsByTraineeUsernameAndFromDate(username, fromDate);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTraineeUsernameAndTrainingType(String username, TrainingType trainingType) {
        List<Training> trainings = trainingRepository.findTrainingsByTraineeUsernameAndTrainingType(username, trainingType);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTraineeUsernameAndTrainerUsername(String username, String trainerUsername) {
        List<Training> trainings = trainingRepository.findTrainingsByTraineeUsernameAndTrainerUsername(username, trainerUsername);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTrainerUsernameAndToDate(String username, Date toDate) {
        List<Training> trainings = trainingRepository.findTrainingsByTrainerUsernameAndToDate(username, toDate);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTrainerUsernameAndFromDate(String username, Date fromDate) {
        List<Training> trainings = trainingRepository.findTrainingsByTrainerUsernameAndFromDate(username, fromDate);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTrainerUsernameAndTrainingType(String username, TrainingType trainingType) {
        List<Training> trainings = trainingRepository.findTrainingsByTrainerUsernameAndTrainingType(username, trainingType);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainingDTO> getTrainingsByTrainerUsernameAndTraineeUsername(String username, String traineeUsername) {
        List<Training> trainings = trainingRepository.findTrainingsByTrainerUsernameAndTraineeUsername(username, traineeUsername);
        return trainings.stream().map(TrainingDTO::toDTO).toList();
    }
}
