package org.example.finalprojectepamlabapplication.repository;

import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.model.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    Optional<Training> findTrainingByTrainingName(String trainingName);

    @Query("SELECT t FROM Training t WHERE t.trainee.user.username = :username AND t.trainingDate <= :toDate")
    List<Training> findTrainingsByTraineeUsernameAndToDate(@Param("username") String username, @Param("toDate") Date toDate);

    @Query("SELECT t FROM Training t WHERE t.trainee.user.username = :username AND t.trainingDate >= :fromDate")
    List<Training> findTrainingsByTraineeUsernameAndFromDate(@Param("username") String username, @Param("fromDate") Date fromDate);

    @Query("SELECT t FROM Training t WHERE t.trainee.user.username = :username AND t.trainingType = :trainingType")
    List<Training> findTrainingsByTraineeUsernameAndTrainingType(@Param("username") String username, @Param("trainingType") TrainingType trainingType);

    @Query("SELECT t FROM Training t WHERE t.trainee.user.username = :username AND t.trainer.user.username = :trainerUsername")
    List<Training> findTrainingsByTraineeUsernameAndTrainerUsername(@Param("username") String username, @Param("trainerUsername") String trainerUsername);

    @Query("SELECT t FROM Training t WHERE t.trainer.user.username = :username AND t.trainingDate <= :toDate")
    List<Training> findTrainingsByTrainerUsernameAndToDate(@Param("username") String username, @Param("toDate") Date toDate);

    @Query("SELECT t FROM Training t WHERE t.trainer.user.username = :username AND t.trainingDate >= :fromDate")
    List<Training> findTrainingsByTrainerUsernameAndFromDate(@Param("username") String username, @Param("fromDate") Date fromDate);

    @Query("SELECT t FROM Training t WHERE t.trainer.user.username = :username AND t.trainingType = :trainingType")
    List<Training> findTrainingsByTrainerUsernameAndTrainingType(@Param("username") String username, @Param("trainingType") TrainingType trainingType);

    @Query("SELECT t FROM Training t WHERE t.trainer.user.username = :username AND t.trainee.user.username = :traineeUsername")
    List<Training> findTrainingsByTrainerUsernameAndTraineeUsername(@Param("username") String username, @Param("traineeUsername") String traineeUsername);
}
