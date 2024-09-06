package org.example.finalprojectepamlabapplication.configuration;

import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.Training;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StorageConfig {
    @Bean
    public Map<Long, Trainee> traineesStorage() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Trainer> trainersStorage() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Training> trainingsStorage() {
        return new HashMap<>();
    }
}
