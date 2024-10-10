package org.example.finalprojectepamlabapplication.controller.implementation;

import jakarta.validation.Valid;
import org.example.finalprojectepamlabapplication.DTO.endpointDTO.ChangeLoginRequestDTO;
import org.example.finalprojectepamlabapplication.DTO.modelDTO.UserDTO;
import org.example.finalprojectepamlabapplication.controller.UserController;
import org.example.finalprojectepamlabapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/{id}")
    public String getUserLogin(@PathVariable Long id){
        UserDTO userDTO = userService.getUserById(id);
        return userDTO.getUsername();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @ModelAttribute @Valid ChangeLoginRequestDTO changeLoginRequestDTO) {
        UserDTO userDTO = userService.getUserById(id);
        if(userDTO.getPassword().equals(changeLoginRequestDTO.getOldPassword())){
            userService.updateUserPassword(userDTO, changeLoginRequestDTO.getNewPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    @PatchMapping("/{id}")
    public void changeStatus(@PathVariable Long id){
        userService.changeActiveStatus(id);
    }
}
