package org.example.finalprojectepamlabapplication.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Trainee {
    private Long id;
    private Date dateOfBirth;
    private String address;
    private User user;
}
