package org.example.finalprojectepamlabapplication.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Trainer {
    private Long id;
    private TrainingType specialization;
    private User user;
}
