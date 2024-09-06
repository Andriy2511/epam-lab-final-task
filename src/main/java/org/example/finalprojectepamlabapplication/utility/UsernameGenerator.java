package org.example.finalprojectepamlabapplication.utility;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.model.User;

import java.util.List;

@Slf4j
public class UsernameGenerator {

    public String generateUsername(User user, List<User> users) {
        String username = user.getFirstName() + "." + user.getLastName();
        int countOfSimilarNameAndSurname = countCoincidenceWithNameAndSurname(user, users);
        if(countOfSimilarNameAndSurname == 0){
            log.info("Generated username: {}", username);
            return username;
        } else {
            username = username + countOfSimilarNameAndSurname;
            log.info("Generated username with suffix: {}", username);
            return username;
        }
    }

    private int countCoincidenceWithNameAndSurname(User user, List<User> users){
        int count = 0;
        for(User u : users){
            if(u.getFirstName().equals(user.getFirstName()) && u.getLastName().equals(user.getLastName())){
                count++;
            }
        }
        return count;
    }
}
