package org.example.finalprojectepamlabapplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.TrainingTypeDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TrainingTypeController {

    @Operation(summary = "Finds all training types",
            description = "The method finds all training types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training types found successfully")
    })
    @GetMapping
    List<TrainingTypeDTO> getAllTrainingTypes();
}
