package org.example.finalprojectepamlabapplication.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private String firstName;
    private String lastName;
    private String username;
    @ToString.Exclude
    private String password;
    private boolean isActive;
}
