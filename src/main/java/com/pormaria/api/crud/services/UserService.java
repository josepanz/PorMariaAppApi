package com.pormaria.api.crud.services;

import com.pormaria.api.crud.models.UserModel;
import com.pormaria.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    public ArrayList<UserModel> getAllUsers() {
        return (ArrayList<UserModel>) iUserRepository.findAll();
    }

    public UserModel saveUser(UserModel userModel) {
        if (!iUserRepository.findById(userModel.getId()).isPresent()) {
            userModel.setCreationDate(new Date());
        }
        return iUserRepository.save(userModel);
    }

    public Optional<UserModel> getUserById(Long id) {
        return iUserRepository.findById(id);
    }


    public Optional<UserModel> getUserByUsername(String username) {
        return Optional.ofNullable(iUserRepository.findByUsername(username));
    }

    public Optional<UserModel> updateUserById(UserModel request, Long id) {
        if (iUserRepository.findById(id).isPresent()) {
            UserModel user = iUserRepository.findById(id).get();
            user.setCode(request.getCode());
            user.setUsername(request.getUsername());
            user.setActive(request.isActive());
            user.setPassword(request.getPassword());
            user.setDisabledDate(request.getDisabledDate());
            user.setCreationDate(request.getCreationDate());
            iUserRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<UserModel> updateUserStatusById(Long id, boolean status) {
        if (iUserRepository.findById(id).isPresent()) {
            UserModel user = iUserRepository.findById(id).get();
            user.setActive(status);
            if (!status) user.setDisabledDate(new Date());
            iUserRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public boolean deleteUserById(Long id) {
        try {
            iUserRepository.deleteById(id);
            System.out.println("User id deleted: " + id);
            return true;
        } catch (Exception e) {
            System.out.println("User id cant deleted: " + id);
            return false;
        }
    }

}
