package org.example.finalprojectepamlabapplication.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.model.Trainee;
import org.example.finalprojectepamlabapplication.model.Trainer;
import org.example.finalprojectepamlabapplication.model.Training;
import org.example.finalprojectepamlabapplication.utility.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
@Slf4j
public class DataInitializer {

    private final Map<Long, Trainee> traineesStorage;

    private final Map<Long, Trainer> trainersStorage;

    private final Map<Long, Training> trainingsStorage;

    @Autowired
    public DataInitializer(Map<Long, Trainee> traineesStorage, Map<Long, Trainer> trainersStorage, Map<Long, Training> trainingsStorage) {
        this.traineesStorage = traineesStorage;
        this.trainersStorage = trainersStorage;
        this.trainingsStorage = trainingsStorage;
    }

    @PostConstruct
    public void initData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = new ClassPathResource("data.json").getInputStream();
            DataWrapper dataWrapper = objectMapper.readValue(inputStream, DataWrapper.class);

            dataWrapper.getTrainees().forEach(trainee -> traineesStorage.put(trainee.getId(), trainee));
            dataWrapper.getTrainers().forEach(trainer -> trainersStorage.put(trainer.getId(), trainer));
            dataWrapper.getTrainings().forEach(training -> trainingsStorage.put(training.getId(), training));

        } catch (IOException e) {
            log.error("Error while reading data: ", e);
            e.printStackTrace();
        }
    }
}



