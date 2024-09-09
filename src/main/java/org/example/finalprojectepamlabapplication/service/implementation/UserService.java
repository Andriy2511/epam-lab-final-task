package org.example.finalprojectepamlabapplication.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.finalprojectepamlabapplication.DAO.IUserDAO;
import org.example.finalprojectepamlabapplication.model.User;
import org.example.finalprojectepamlabapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    private final IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.findAllUsers();
        log.info("Searching all users. Total users: {}", users.size());
        return users;
    }
}
