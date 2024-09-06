package org.example.finalprojectepamlabapplication.DAO;

import org.example.finalprojectepamlabapplication.model.Training;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ITrainingDAO {
    Optional<Training> addTraining(Training training);
    Optional<Training> findById(Long id);
}
