package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody  UserDTO userDTO) {
        User newUser = userService.createUse(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> allUsers = userService.getAllUsers(pageable);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }
}
