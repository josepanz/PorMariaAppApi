package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.UserModel;
import com.pormaria.api.crud.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public String getAllUsersPage(Model model, @Param("keyword") String keyword) {
        ArrayList<UserModel> userModelList = new ArrayList<>();
        try {
            if (keyword == null) {
                userModelList = this.userService.getAllUsers();
            } else {
                for (UserModel user : this.userService.getAllUsers()) {
                    if (StringUtils.containsIgnoreCase(user.toString(), keyword)) {
                        userModelList.add(user);
                    }
                }
            }
            model.addAttribute("keyword", keyword);
            model.addAttribute("users", userModelList);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "users";
    }
}
