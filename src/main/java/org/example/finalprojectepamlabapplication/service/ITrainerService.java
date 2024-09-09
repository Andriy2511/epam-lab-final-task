package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.model.Trainer;

import java.util.Optional;

public interface ITrainerService {

    Optional<Trainer> addTrainer(Trainer trainer);

    Optional<Trainer> updateTrainer(Trainer trainer);

    Optional<Trainer> deleteTrainer(Trainer trainer);

    Optional<Trainer> getTrainerById(Long id);

}
