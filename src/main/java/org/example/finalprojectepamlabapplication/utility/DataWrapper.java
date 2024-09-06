package org.example.finalprojectepamlabapplication.utility;

import lombok.Getter;
import lombok.Setter;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.Training;

import java.util.List;

@Getter
@Setter
public class DataWrapper {
    private List<Trainee> trainees;
    private List<Trainer> trainers;
    private List<Training> trainings;
}
