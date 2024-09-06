package org.example.finalprojectepamlabapplication.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class PasswordGenerator {
    private static final int PASSWORD_LENGTH = 10;

    public static String generatePassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            char randomChar = (char) (33 + random.nextInt(94));
            password.append(randomChar);
        }

        log.info("The password has been created");
        return password.toString();
    }
}
