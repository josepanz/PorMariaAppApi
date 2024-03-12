package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.UserModel;
import com.pormaria.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    public UserRestController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/getAllUsers")
    public ArrayList<UserModel> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping(path = "/postUser")
    public UserModel insertUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/getUser/id/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping(path = "/getUser/username/{username}")
    public Optional<UserModel> getUserByUsername(@PathVariable("username") String username) {
        return this.userService.getUserByUsername(username);
    }

    @PostMapping(path = "/login")
    public Optional<UserModel> loginUser(@RequestBody Map<String, Object> request) {
        String username = (String) request.get("username");
        String password = (String) request.get("password");
        Optional<UserModel> user = this.userService.getUserByUsername(username);
        return user.map(userModel -> passwordEncoder.matches(password, userModel.getPassword()) ? userModel : null);
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
