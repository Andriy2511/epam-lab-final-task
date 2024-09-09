package org.example.finalprojectepamlabapplication.DAO;

import org.example.finalprojectepamlabapplication.model.User;

import java.util.List;

public interface IUserDAO {

    List<User>  findAllUsers();

}
