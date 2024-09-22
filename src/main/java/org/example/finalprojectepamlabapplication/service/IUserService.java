package org.example.finalprojectepamlabapplication.service;

import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO updateUserPassword(UserDTO userDTO, String password);

    UserDTO changeActiveStatus(Long id);
}
