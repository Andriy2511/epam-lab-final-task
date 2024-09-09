package org.example.finalprojectepamlabapplication.DAO;

import org.example.finalprojectepamlabapplication.model.Trainee;

import java.util.Optional;

public interface ITraineeDAO {

    Optional<Trainee> addTrainee(Trainee trainee);

    Optional<Trainee> updateTrainee(Trainee trainee);

    Optional<Trainee> findById(Long id);

    Optional<Trainee> deleteTrainee(Long id);

    Long findMaxId();
}
