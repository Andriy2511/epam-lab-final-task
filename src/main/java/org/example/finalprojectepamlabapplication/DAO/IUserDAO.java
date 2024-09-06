package org.example.finalprojectepamlabapplication.DAO;

import org.example.finalprojectepamlabapplication.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<User>  findAllUsers();
}
