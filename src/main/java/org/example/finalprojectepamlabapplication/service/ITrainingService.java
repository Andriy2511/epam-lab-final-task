package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.model.Training;

import java.util.Optional;

public interface ITrainingService {
    Optional<Training> addTraining(Training training);
    Optional<Training> getTrainingById(Long id);
}
