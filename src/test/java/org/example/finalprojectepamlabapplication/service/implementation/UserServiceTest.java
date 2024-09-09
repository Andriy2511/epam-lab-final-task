package org.example.finalprojectepamlabapplication.service.implementation;

import org.example.finalprojectepamlabapplication.DAO.IUserDAO;
import org.example.finalprojectepamlabapplication.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private UserService userService;

    private List<User> userList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userList = new ArrayList<>();
        userList.add(new User("Test", "User", "", "", true));
        userList.add(new User("Test2", "User2", "", "", true));

    }

    @Test
    public void testGetAllUsers() {
        when(userDAO.findAllUsers()).thenReturn(userList);
        List<User> result = userService.getAllUsers();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(userList, result);
    }
}

