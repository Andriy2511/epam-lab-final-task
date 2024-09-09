package org.example.finalprojectepamlabapplication.DAO.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.ITrainerDAO;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class TrainerDAO implements ITrainerDAO {
    private final Map<Long, Trainer> trainerMap;

    @Autowired
    public TrainerDAO(Map<Long, Trainer> trainerMap) {
        this.trainerMap = trainerMap;
    }

    @Override
    public Optional<Trainer> addTrainer(Trainer trainer) {
        trainerMap.put(trainer.getId(), trainer);
        return Optional.of(trainer);
    }

    @Override
    public Optional<Trainer> updateTrainer(Trainer trainer) {
        return Optional.ofNullable(trainerMap.replace(trainer.getId(), trainer));
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        Optional<Trainer> searchedTrainer = Optional.ofNullable(trainerMap.get(id));
        if (searchedTrainer.isEmpty()) {
            log.warn("Searched Trainer with id {} not found.", id);
        }
        return searchedTrainer;
    }

    @Override
    public Optional<Trainer> deleteTrainer(Long id) {
        Optional<Trainer> removedTrainer = Optional.ofNullable(trainerMap.remove(id));
        if (removedTrainer.isEmpty()) {
            log.warn("Failed to delete Trainer with id {}. Trainee not found.", id);
        }
        return removedTrainer;
    }

    @Override
    public Long findMaxId() {
        Long maxId = 0L;
        for (Long id : trainerMap.keySet()) {
            if (id > maxId)
                maxId = id;
        }
        log.info("Max id is {}", maxId);
        return maxId;
    }
}
