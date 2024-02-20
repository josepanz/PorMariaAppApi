package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.UserModel;
import com.pormaria.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/getAllUsers")
    public ArrayList<UserModel> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping(path = "/postUser")
    public UserModel insertUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/getUser/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @PutMapping(path = "/update/{id}")
    public Optional<UserModel> updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id) {
        return this.userService.updateUserById(request, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteUserById(@PathVariable("id") Long id) {
        return this.userService.deleteUserById(id);
    }
}
