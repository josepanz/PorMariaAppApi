package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.UserModel;
import com.pormaria.api.crud.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

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

    @GetMapping("/users/new")
    public String addUser(Model model) {
        UserModel userModel = new UserModel();
        userModel.setActive(true);
        userModel.setCreationDate(new Date());
        model.addAttribute("user", userModel);
        model.addAttribute("pageTitle", "Crear nuevo Usuario");

        return "user_form";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @PostMapping("/users/save")
    public String saveUser(UserModel user, RedirectAttributes redirectAttributes) {
        try {
            if (!user.isActive()) {
                user.setDisabledDate(new Date());
            } else {
                user.setDisabledDate(null);
            }
            this.userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "El usuario fue creado satisfactoriamente!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String editUser(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Optional<UserModel> user = this.userService.getUserById(id);

            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Editar usuario (ID: " + id + ")");

            return "user_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUsers(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            this.userService.deleteUserById(id);

            redirectAttributes.addFlashAttribute("message", "El usuario con id=" + id + " fue eliminado satisfactoriamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

    @GetMapping("/users/{id}/active/{status}")
    public String updateUserStatus(@PathVariable("id") Long id, @PathVariable("status") boolean active,
                                   Model model, RedirectAttributes redirectAttributes) {
        try {
            this.userService.updateUserStatusById(id, active);

            String status = active ? "activado" : "desactivado";
            String message = "El usuario id=" + id + " fue " + status;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }

}
