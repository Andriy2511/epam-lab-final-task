package org.example.finalprojectepamlabapplication.DAO;

import org.example.finalprojectepamlabapplication.model.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ITrainerDAO {
    Optional<Trainer> addTrainer(Trainer trainee);
    Optional<Trainer> updateTrainer(Trainer trainee);
    Optional<Trainer> findById(Long id);
    Optional<Trainer> deleteTrainer(Long id);
}
