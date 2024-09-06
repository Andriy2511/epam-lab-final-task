package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.model.Trainee;

import java.util.Optional;

public interface ITraineeService {
    Optional<Trainee> addTrainee(Trainee trainee);
    Optional<Trainee> updateTrainee(Trainee trainee);
    Optional<Trainee> deleteTrainee(Trainee trainee);
    Optional<Trainee> getTraineeById(Long id);
}
