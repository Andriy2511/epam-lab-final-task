package org.example.finalprojectepamlabapplication.DAO;

import org.example.finalprojectepamlabapplication.model.Training;

import java.util.Optional;

public interface ITrainingDAO {

    Optional<Training> addTraining(Training training);

    Optional<Training> findById(Long id);

    Long findMaxId();
}
